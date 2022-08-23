package com.kkb.service.cpregistermanager.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kkb.bean.Behospital;
import com.kkb.bean.Chargeproject;
import com.kkb.bean.Cpregister;
import com.kkb.dao.BehospitalMapper;
import com.kkb.dao.CpregisterMapper;
import com.kkb.service.cpregistermanager.CpregisterManagerService;
import com.kkb.utils.FileUtils;
import com.kkb.vo.cpregistermanager.req.AddNewCPParam;
import com.kkb.vo.cpregistermanager.req.SelectCPByNameReq;
import com.kkb.vo.cpregistermanager.req.SelectCpregisterReq;
import com.kkb.vo.cpregistermanager.req.SelectOneParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CpregisterManagerServiceImpl implements CpregisterManagerService {

    @Autowired
    private CpregisterMapper cpregisterMapper;

    @Autowired
    private BehospitalMapper behospitalMapper;

    private static final String[] TITLES ={"病历号","病人姓名","收费项目","收费金额","检查日期"};

    /**
     * 查询收费项目登记
     * @param param
     * @return
     */
    @Override
    public PageInfo<Cpregister> queryCpregisterByParam(SelectCpregisterReq param) {
        PageHelper.startPage(param.getPageNum(),param.getPageSize());
        List<Cpregister> cpregisterList = cpregisterMapper.selectCpregisterByParam(param);
        return new PageInfo<>(cpregisterList);
    }

    @Override
    public PageInfo<Cpregister> selectCpregisterById(SelectOneParam param) {
        PageHelper.startPage(param.getPageNum(),param.getPageSize());
        Integer hosrId = param.getHosrId();
        List<Cpregister> cpregisterList = cpregisterMapper.selectCpregisterById(hosrId);
        return new PageInfo<>(cpregisterList);
    }

    @Override
    public Chargeproject selectChargeProject(SelectCPByNameReq param) {
        Chargeproject chargeproject = cpregisterMapper.selectChargeProject(param);
        return chargeproject;
    }

    @Override
    public int insertByParam(AddNewCPParam param) {
        int i = cpregisterMapper.insertByParam(param);
        return i;
    }

    @Override
    public Behospital selectBehospitalById(SelectOneParam param) {
        Integer behId = param.getHosrId();
        Behospital behospital = behospitalMapper.selectByPrimaryKey(behId);
        return behospital;
    }

    @Override
    public Integer selectCost(SelectOneParam param) {
        Integer hosrId = param.getHosrId();
        Integer cost = cpregisterMapper.selectCost(hosrId);
        return cost;
    }

    @Override
    public void exportExcelCPRegister(Integer hosrId) {
        List<Cpregister> cpregisterList = cpregisterMapper.selectCpregisterById(hosrId);
        try {
            String cpRegisterExcel = FileUtils.createCPRegisterExcel(TITLES, cpregisterList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
