package com.kaikeba.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kaikeba.mapper.ChargeMapper;
import com.kaikeba.pojo.*;
import com.kaikeba.vo.QueryChargeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 收费管理的逻辑层
 */
@Service
public class ChargeService {
    @Autowired
    private ChargeMapper chargeMapper;
    @Autowired
    private ChargeHospitalService chargeHospitalService;
    @Autowired
    private HospitalService hospitalService;
    @Autowired
    private RegisterService registerService;

    /**
     * 分页查询收费项目（检查收费项目登记页面使用）
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED,readOnly = true)
    public Map<String,Object> queryByPage(Integer hospitalId,Integer pageNum,Integer pageSize){
        ChargeExample chargeExample=new ChargeExample();
        ChargeExample.Criteria criteria = chargeExample.createCriteria();
        List<ChargeHospital> chargeHospitals = chargeHospitalService.queryByHospitalId(hospitalId);
        List list=new ArrayList<>();
        for (ChargeHospital chargeHospital : chargeHospitals) {
            list.add(chargeHospital.getChargeId());
        }
        criteria.andIdIn(list);
        Hospital hospital = hospitalService.queryById(hospitalId);
        Register register = registerService.findById(hospital.getRegisterId());
        if (pageNum!= null&&pageSize!=null){
            PageHelper.startPage(pageNum,pageSize);
        }
        List<Charge> charges = chargeMapper.selectByExample(chargeExample);

        List<Charge> chargeList=chargeMapper.selectByExample(null);
        PageInfo<Charge> pageInfo=new PageInfo<>(charges);
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        Double money=0.0;
        for (Charge charge : charges) {
            charge.setRegister(register);
            String format = simpleDateFormat.format(charge.getChargeDate());
            charge.setChargeDateFormat(format);
        }
        for (Charge charge : chargeList) {
            money+=charge.getChargeMoney();
        }
        hospital.setTotalCost(money);
        hospital.setRestCost(hospital.getDeposit()- hospital.getTotalCost());
        Map map=new HashMap();
        map.put("charges",charges);
        map.put("hospital",hospital);
        map.put("pageInfo",pageInfo);
        return map;
    }

    /**
     * 多条件分页查询收费项目（收费项目管理页面使用）
     * @param pageNum
     * @param pageSize
     * @param vo
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED,readOnly = true)
    public PageInfo<Charge> queryByPage2(Integer pageNum, Integer pageSize, QueryChargeVO vo){
        ChargeExample chargeExample=new ChargeExample();
        ChargeExample.Criteria criteria = chargeExample.createCriteria();
        if (vo!=null){
            if (vo.getChargeName()!=null&&!"".equals(vo.getChargeName())){
                criteria.andChargeNameLike("%"+vo.getChargeName()+"%");
            }
        }
        if (pageNum!=null&&pageSize!=null){
            PageHelper.startPage(pageNum,pageSize);
        }
        List<Charge> chargeList = chargeMapper.selectByExample(chargeExample);
        return new PageInfo<>(chargeList);
    }

    /**
     * 添加收费项目
     * @param charge
     */
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = {Exception.class})
    public Integer addCharge(Integer hospitalId,Charge charge){
        int i = chargeMapper.insertSelective(charge);
        Charge charge1 = queryByName(charge.getChargeName());
        int j = chargeHospitalService.add(hospitalId, charge1.getId());
        return i+j;
    }

    /**
     * 添加收费项目前的准备工作（多表查询）
     * @param id
     */
    @Transactional(propagation = Propagation.REQUIRED,readOnly = true)
    public List addAdvance(Integer id){
        Hospital hospital = hospitalService.queryById(id);
        Register register = registerService.findById(hospital.getRegisterId());
        List list=new ArrayList();
        list.add(hospital);
        list.add(register);
        return list;
    }

    /**
     * 根据收费项目名称查询收费项目信息
     * @param chargeName
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED,readOnly = true)
    public Charge queryByName(String chargeName){
        ChargeExample chargeExample=new ChargeExample();
        ChargeExample.Criteria criteria = chargeExample.createCriteria();
        if (chargeName!=null&&!"".equals(chargeName)){
            criteria.andChargeNameEqualTo(chargeName);
        }
        List<Charge> chargeList = chargeMapper.selectByExample(chargeExample);
        return chargeList.get(0);
    }

    /**
     * 通过主键查询收费项目信息
     * @param id
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED,readOnly = true)
    public Charge queryById(Integer id){
        return chargeMapper.selectByPrimaryKey(id);
    }

    /**
     * 更新收费项目信息
     * @param charge
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = {Exception.class})
    public int updateCharge(Charge charge){
        return chargeMapper.updateByPrimaryKeySelective(charge);
    }

    /**
     * 删除收费项目信息
     * @param id
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = {Exception.class})
    public int deleteCharge(int id){
        Charge charge=new Charge();
        charge.setId(id);
        charge.setIsDel(1);
        return chargeMapper.updateByPrimaryKeySelective(charge);
    }
}
