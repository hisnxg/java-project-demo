package com.kkb.service.registration.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kkb.bean.Doctor;
import com.kkb.bean.Hosregister;
import com.kkb.dao.DoctorMapper;
import com.kkb.dao.HosregisterMapper;
import com.kkb.myenum.HospitalSettleEnum;
import com.kkb.myenum.HosrStateEnum;
import com.kkb.service.registration.RegistrationService;
import com.kkb.utils.FileUtils;
import com.kkb.vo.hospzation.resp.BeHospitalResp;
import com.kkb.vo.hospzation.resp.BeHospitalTotal;
import com.kkb.vo.registration.req.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    @Autowired
    private HosregisterMapper hosregisterMapper;

    private final String[] TITLES = {"病历号", "姓名", "身份证号", "社保号", "联系电话"};

    @Override
    public PageInfo<Hosregister> queryHosregisterByParam(SelectRegistrationReq param) {
        PageHelper.startPage(param.getPageNum(), param.getPageSize());
        List<Hosregister> hosregisterList = hosregisterMapper.queryHosregisterByParam(param);
        return new PageInfo<>(hosregisterList);

    }

    //添加挂号信息
    @Override
    public void addHosregister(AddRegistrationParam param) {
        Hosregister hosregister = new Hosregister();
        BeanUtils.copyProperties(param, hosregister);
        hosregisterMapper.insertSelective(hosregister);
    }

    //退号
    @Override
    public String updateById(Integer[] hosrIds) {
        List<Integer> list = new ArrayList<>();
        for (Integer hosrId : hosrIds) {
            Hosregister hosregister = new Hosregister();
            hosregister.setHosrId(hosrId);
            hosregister.setHosrState(HosrStateEnum.NO_REGISTER.getCode());
            //查询当前挂号状态
            Hosregister hosregisterYanZheng = hosregisterMapper.selectByPrimaryKey(hosrId);
            //判断是否是否是已出院/以退院状态
            if (!hosregisterYanZheng.getHosrState().equals(HosrStateEnum.REGISTER.getCode())) {
                list.add(hosrId);
            } else {
                //修改挂号状态
                hosregisterMapper.updateByPrimaryKeySelective(hosregister);
            }
        }
        //返回给前台的提示信息
        if (!CollectionUtils.isEmpty(list)) {
            return "挂号id" + list.toString() + ", 状态不是挂号，不能修改其状态";
        }else {
            return "";
        }
    }

    //根据hosrId查找信息
    @Override
    public Hosregister FindByHosrIdParam(FindByHosrIdParam param) {
        Integer hosrId = param.getHosrId();
        Hosregister hosregister = hosregisterMapper.FindByHosrIdParam(hosrId);
        return hosregister;
    }

    @Override
    public List<Doctor> findAllKeshi() {
        List<Doctor> allKeshi = hosregisterMapper.findAllKeshi();
        return allKeshi;
    }

    @Override
    public List<Doctor> getDoctorNameByKeshi(GetDoctorNameByKeshiParam param) {
        List<Doctor> list = hosregisterMapper.getDoctorNameByKeshi(param);
        return list;
    }

    @Override
    public Doctor findByDoctorId(Integer dId) {
        return hosregisterMapper.findByDoctorId(dId);
    }

    @Override
    public void exportExcel(Integer[] ids) {
        List<BeHosregisterResp> list = hosregisterMapper.selectHosregisterIds(ids);
        try {
            FileUtils.hosregisterExcel(TITLES, list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateRegistration(UpdateRegistrationParam param) {
        hosregisterMapper.updateRegistration(param);
    }
}
