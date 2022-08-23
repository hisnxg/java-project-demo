package com.nxg.service;

import com.nxg.mapper.ChargeHospitalMapper;
import com.nxg.pojo.ChargeHospital;
import com.nxg.pojo.ChargeHospitalExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *  收费项目-住院管理的逻辑层
 */
@Service
public class ChargeHospitalService {

    @Autowired
    private ChargeHospitalMapper chargeHospitalMapper;


    /**
     * 根据住院信息id查询收费项目列表
     * @param hospitalId
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED,readOnly = true)
    public List<ChargeHospital> queryByHospitalId(Integer hospitalId){
        ChargeHospitalExample chargeHospitalExample = new ChargeHospitalExample();
        ChargeHospitalExample.Criteria criteria = chargeHospitalExample.createCriteria();
        if (hospitalId!=null&&hospitalId>0){
            criteria.andHospitalIdEqualTo(hospitalId);
        }
        List<ChargeHospital> chargeHospitals = chargeHospitalMapper.selectByExample(chargeHospitalExample);
        return chargeHospitals;
    }

    /**
     * 添加医院-收费项目的中间表
     * @param hospitalId
     * @param chargeId
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = {Exception.class})
    public int add(Integer hospitalId,Integer chargeId){
        ChargeHospital chargeHospital = new ChargeHospital();
        chargeHospital.setHospitalId(hospitalId);
        chargeHospital.setChargeId(chargeId);
        return chargeHospitalMapper.insertSelective(chargeHospital);
    }
}
