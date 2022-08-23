package com.kkb.service.hospitalsettle.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kkb.bean.Behospital;
import com.kkb.dao.BehospitalMapper;
import com.kkb.service.behospital.BeHospitalService;
import com.kkb.service.cpregistermanager.CpregisterManagerService;
import com.kkb.service.hospitalsettle.HospitalSettleService;
import com.kkb.utils.FileUtils;
import com.kkb.vo.cpregistermanager.req.SelectOneParam;
import com.kkb.vo.hospitalsettle.req.HospitalSettleReq;
import com.kkb.vo.hospitalsettle.req.UpdateHospitalSettleStatusReq;
import com.kkb.vo.hospitalsettle.resp.HospitalSettleResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author mj
 * @create 2021-03-23 10:38
 * @describe:
 */
@Service
public class HospitalSettleServiceImpl implements HospitalSettleService {

    @Autowired
    private BehospitalMapper behospitalMapper;

    @Autowired
    private BeHospitalService beHospitalService;

    @Autowired
    private CpregisterManagerService cpregisterManagerService;

    @Autowired
    private static final String[] SETTLE = {"病历号", "姓名", "押金", "当前余额", "状态"};

    @Override
    public Map<String, Object> selectHospitalSettle(HospitalSettleReq param) {
        Map<String, Object> map = new HashMap<>();
        PageHelper.startPage(param.getPageNum(), param.getPageSize());
        List<HospitalSettleResp> list = behospitalMapper.selectHospitalSettle(param);
        //计算余额
        List<HospitalSettleResp> collect = list.stream().map(item -> {
            SelectOneParam selectOneParam = new SelectOneParam();
            selectOneParam.setHosrId(item.getBehId());
            int total = 0;
            Integer price = cpregisterManagerService.selectCost(selectOneParam);

            if (price != null) {
                total = price;
            }
            //设置余额
            item.setBalance(item.getBehAntecedent().doubleValue() - total);
            return item;
        }).collect(Collectors.toList());
        PageInfo<HospitalSettleResp> pageInfo = new PageInfo<>(collect);
        map.put("settlePage", pageInfo);
        return map;
    }

    /**
     * 修改项目的结算状态
     *
     * @param param
     * @return
     */
    @Override
    public String updateHospitalSettleStatus(UpdateHospitalSettleStatusReq param) {

        Behospital behospital1 = behospitalMapper.selectByPrimaryKey(param.getId());

        //1、计算是否存在欠费、还有余额的情况
        String str = "";
        SelectOneParam selectOneParam = new SelectOneParam();
        selectOneParam.setHosrId(param.getId());
        int total = 0;
        Integer price = cpregisterManagerService.selectCost(selectOneParam);

        if (price != null) {
            total = price;
        }
        double balance = behospital1.getBehAntecedent().doubleValue() - total;
        if (balance >= 0) {
            str = "结算成功，该用户还存在【" + balance + "】余额，退还给您【" + balance + "】元";
        } else if (balance < 0) {
            str = "结算失败，该用户还欠费【" + balance + "】元，请交完欠费后再次结算";
            return str;
        }

        //修改项目结算状态为结算
        Behospital behospital = new Behospital();
        behospital.setBehId(param.getId());
        behospital.setBehCloseprice(param.getStatus());
        behospital.setBehAntecedent(new BigDecimal("-" + Double.toString(balance)));
        behospitalMapper.updateByPrimaryKeySelective(behospital);
        return str;
    }

    /**
     * 导出excel
     *
     * @param ids
     * @return
     */
    @Override
    public void exportExcel(Integer[] ids) {
        List<HospitalSettleResp> list = behospitalMapper.exportExcel(ids);
        try {
            FileUtils.createHospitalSettleExcel(SETTLE, list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
