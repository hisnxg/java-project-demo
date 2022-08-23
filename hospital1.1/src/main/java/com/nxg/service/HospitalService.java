package com.nxg.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nxg.mapper.HospitalMapper;
import com.nxg.pojo.Hospital;
import com.nxg.pojo.HospitalExample;
import com.nxg.pojo.Register;
import com.nxg.vo.QueryHospitalVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 *   住院管理的逻辑层
 */
@Service
public class HospitalService {
    @Autowired
    private HospitalMapper hospitalMapper;
    @Autowired
    private RegisterService registerService;
    @Autowired
    private ChargeHospitalService chargeHospitalService;

    /**
     * 分页查询住院信息
     * @param pageNum
     * @param pageSize
     * @param vo
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED,readOnly = true)
    public PageInfo<Hospital> queryByPage(Integer pageNum, Integer pageSize, QueryHospitalVO vo){
        HospitalExample hospitalExample=new HospitalExample();
        HospitalExample.Criteria criteria = hospitalExample.createCriteria();
        if (vo!=null){
            if (vo.getId()!=null&&vo.getId()>0){
                criteria.andIdEqualTo(vo.getId());
            }
            if (vo.getRegisterName()!=null&&!"".equals(vo.getRegisterName())){
                List list=new ArrayList();
                List<Register> registers = registerService.queryByName(vo.getRegisterName());
                for (Register register : registers) {
                    list.add(register.getId());
                }
                criteria.andRegisterIdIn(list);
            }
        }
        if (pageNum!=null&&pageSize!=null){
            PageHelper.startPage(pageNum,pageSize);
        }
        List<Hospital> hospitals = hospitalMapper.selectByExample(hospitalExample);
        for (Hospital hospital : hospitals) {
            Register register = registerService.findById(hospital.getRegisterId());
            hospital.setRegister(register);
        }
        return new PageInfo<>(hospitals);
    }

    /**
     * 根据主键查询住院信息
     * @param id
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED,readOnly = true)
    public Hospital queryById(Integer id){
        if (id!=null&&id>0){
            return hospitalMapper.selectByPrimaryKey(id);
        }
        return null;
    }
}
