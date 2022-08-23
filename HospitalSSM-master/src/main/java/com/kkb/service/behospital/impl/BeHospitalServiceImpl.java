package com.kkb.service.behospital.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kkb.bean.Behospital;
import com.kkb.bean.Doctor;
import com.kkb.bean.Hosregister;
import com.kkb.dao.BehospitalMapper;
import com.kkb.dao.ChargeprojectMapper;
import com.kkb.dao.DoctorMapper;
import com.kkb.dao.HosregisterMapper;
import com.kkb.myenum.HospitalSettleEnum;
import com.kkb.myenum.HosrStateEnum;
import com.kkb.myenum.ShowJiaoFeiEnum;
import com.kkb.service.behospital.BeHospitalService;
import com.kkb.service.cpregistermanager.CpregisterManagerService;
import com.kkb.utils.DateUtils;
import com.kkb.utils.FileUtils;
import com.kkb.vo.cpregistermanager.req.SelectOneParam;
import com.kkb.vo.hospzation.req.BeHospitalReq;
import com.kkb.vo.hospzation.req.BeHospitalTotalReq;
import com.kkb.vo.hospzation.req.UpdateBeHospitalPriceReq;
import com.kkb.vo.hospzation.req.UpdateBehStateStatusReq;
import com.kkb.vo.hospzation.resp.BeHospitalChargeResp;
import com.kkb.vo.hospzation.resp.BeHospitalResp;
import com.kkb.vo.hospzation.resp.BeHospitalTotal;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author mj
 * @create 2021-03-22 23:52
 * @describe:
 */
@Service
public class BeHospitalServiceImpl implements BeHospitalService {

    @Autowired
    private BehospitalMapper behospitalMapper;

    @Autowired
    private HosregisterMapper hosregisterMapper;

    @Autowired
    private ChargeprojectMapper chargeprojectMapper;

    @Autowired
    private DoctorMapper doctorMapper;

    @Autowired
    private CpregisterManagerService cpregisterManagerService;

    private final String[] TITLES = {"病历号", "姓名", "床位号", "联系电话", "押金", "主治医生", "入院时间", "科室", "状态"};

    @Override
    public Map<String, Object> selectBeHospital(BeHospitalReq param) {
        Map<String, Object> map = new HashMap<>();
        PageHelper.startPage(param.getPageNum(), param.getPageSize());
        List<BeHospitalResp> beHospitalResps = behospitalMapper.selectBeHospital(param);
        //查询每一个的住院是否显示需要缴费
        List<BeHospitalResp> beHospitalRespList = beHospitalResps.stream().map(item -> {
            SelectOneParam selectOneParam = new SelectOneParam();
            selectOneParam.setHosrId(item.getBehId());
            int total = 0;
            Integer price = cpregisterManagerService.selectCost(selectOneParam);

            if (price!=null){
                total = price;
            }
            //判断当前押金是否大于消费项目总额，如果是：则显示
            if (item.getBehAntecedent().intValue() >= total) {
                item.setPriceStatus(ShowJiaoFeiEnum.NO_SHOW.getCode());
            } else {
                item.setPriceStatus(ShowJiaoFeiEnum.SHOW.getCode());
            }

            return item;
        }).collect(Collectors.toList());
        PageInfo<BeHospitalResp> pageInfo = new PageInfo<>(beHospitalRespList);
        map.put("pageInfo", pageInfo);
        return map;
    }

    /**
     * 添加住院信息
     *
     * @param param
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addBeHospital(Behospital param) {
        param.setBehCloseprice(HospitalSettleEnum.NO_SETTLE.getCode());
        param.setBehIntime(DateUtils.dateToStr(new Date()));
        //修改挂号状态为住院
        Hosregister hosregister = new Hosregister();
        hosregister.setHosrId(param.getBehId());
        hosregister.setHosrState(HosrStateEnum.HOSPITALIZED.getCode());
        hosregisterMapper.updateByPrimaryKeySelective(hosregister);
        behospitalMapper.insertSelective(param);
    }

    /**
     * 修改挂号状态
     *
     * @param param
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String updateBehStateStatus(UpdateBehStateStatusReq param) {
        List<Integer> list = new ArrayList<>();
        List<Integer> tuiList = new ArrayList<>();
        for (Integer hosrId : param.getHosrId()) {
            Hosregister hosregister = new Hosregister();
            hosregister.setHosrId(hosrId);
            hosregister.setHosrState(param.getHosrState());
            //查询是否是已结算
            BeHospitalTotal beHospitalTotal = behospitalMapper.selectBehStateById(hosrId);
            if (beHospitalTotal.getBehCloseprice().equals(HospitalSettleEnum.SETTLE.getCode())){
                //查询当前挂号状态
                Hosregister hosregisterYanZheng = hosregisterMapper.selectByPrimaryKey(hosrId);
                //判断是否是否是已出院/以退院状态
                if (hosregisterYanZheng.getHosrState().equals(HosrStateEnum.DISCHARGED.getCode()) ||
                        hosregisterYanZheng.getHosrState().equals(HosrStateEnum.TUI_YUAN.getCode()) ||
                        hosregisterYanZheng.getHosrState().equals(HosrStateEnum.NO_REGISTER.getCode())) {
                    tuiList.add(hosrId);
                }else {
                    //修改挂号状态
                    hosregisterMapper.updateByPrimaryKeySelective(hosregister);
                }
            }else {
                list.add(hosrId);
            }
        }
        //返回给前台的提示信息
        if (!CollectionUtils.isEmpty(list)) {
            return "挂号id" + list.toString() + ", 还未结算，不能修改状态，其他id已修改";
        }else if (!CollectionUtils.isEmpty(tuiList)){
            return "挂号id" + tuiList.toString() + ", 已出院/已退院/已退号，无需更改！";
        }else {
            return "";
        }
    }

    /**
     * 根据挂号id，查询住院信息
     *
     * @param hosrId
     * @return
     */
    @Override
    public Map<String, Object> selectBeHospitalStateById(Integer hosrId) {
        Map<String, Object> map = new HashMap<>();
        BeHospitalTotal beHospitalTotal = new BeHospitalTotal();
        //BeHospitalTotal beHospitalTotal = behospitalMapper.selectBehStateById(hosrId);
        //根据挂号id，查询挂号信息
        Hosregister hosregister = hosregisterMapper.selectByPrimaryKey(hosrId);
        //判空
        if (hosregister==null){
            map.put("totalMsg", null);
            return map;
        }
        //属性对拷
        BeanUtils.copyProperties(hosregister, beHospitalTotal);
        //根据挂号id，查询是否已住院
        Behospital behospital = behospitalMapper.selectByPrimaryKey(hosrId);
        //查询医生信息
        Doctor doctor = doctorMapper.selectByPrimaryKey(hosregister.getDoctor().getdId());
        if (doctor!=null) {
            beHospitalTotal.setKeShi(doctor.getdKeshi());
            beHospitalTotal.setDoctorName(doctor.getdName());
        }
        //判断该挂号是否已经住院
        if (behospital != null) {
            beHospitalTotal.setPrimaryStatus(1);
            BeanUtils.copyProperties(behospital, beHospitalTotal);
        } else {
            beHospitalTotal.setPrimaryStatus(0);
            //判断挂号id是否已退号
            if (hosregister.getHosrState().equals(HosrStateEnum.NO_REGISTER.getCode())){
                beHospitalTotal.setPrimaryStatus(2);
            }
        }
        map.put("totalMsg", beHospitalTotal);
        return map;
    }

    /**
     * 缴纳押金
     *
     * @param param
     * @return
     */
    @Override
    public void updateBeHospitalPrice(UpdateBeHospitalPriceReq param) {
        Behospital behospital = new Behospital();
        behospital.setBehId(param.getHosrId());
        behospital.setBehAntecedent(param.getPrice());
        behospitalMapper.updateByPrimaryKeySelective(behospital);
    }

    /**
     * 删除住院信息
     *
     * @param hospIds
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBeHospital(Integer[] hospIds) {
        for (Integer hospId : hospIds) {
            behospitalMapper.deleteByPrimaryKey(hospId);
        }
    }

    /**
     * 修改住院信息
     *
     * @param param
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateBeHospital(BeHospitalTotalReq param) {
        Behospital behospital = new Behospital();
        Hosregister hosregister = new Hosregister();
        BeanUtils.copyProperties(param, behospital);
        BeanUtils.copyProperties(param, hosregister);
        hosregister.setHosrId(behospital.getBehId());
        behospitalMapper.updateByPrimaryKeySelective(behospital);
        //hosregisterMapper.updateByPrimaryKeySelective(hosregister);
    }

    @Override
    public List<BeHospitalChargeResp> selectPrice(Integer id) {
        return chargeprojectMapper.selectPrice(id);
    }

    @Override
    public void exportExcel(Integer[] ids) {
        List<BeHospitalResp> list = behospitalMapper.selectBeHospitalByIds(ids);
        try {
            FileUtils.createHospitalExcel(TITLES, list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
