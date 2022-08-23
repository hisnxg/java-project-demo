package com.kkb.service.cpregistermanager;

import com.github.pagehelper.PageInfo;
import com.kkb.bean.Behospital;
import com.kkb.bean.Chargeproject;
import com.kkb.bean.Cpregister;
import com.kkb.vo.cpregistermanager.req.AddNewCPParam;
import com.kkb.vo.cpregistermanager.req.SelectCPByNameReq;
import com.kkb.vo.cpregistermanager.req.SelectCpregisterReq;
import com.kkb.vo.cpregistermanager.req.SelectOneParam;

import java.util.List;
import java.util.Map;


public interface CpregisterManagerService {
    //参数查询收费项目登记
    PageInfo<Cpregister> queryCpregisterByParam(SelectCpregisterReq param);

    PageInfo<Cpregister> selectCpregisterById(SelectOneParam param);

    Chargeproject selectChargeProject(SelectCPByNameReq param);

    int insertByParam(AddNewCPParam param);

    Behospital selectBehospitalById(SelectOneParam param);

    Integer selectCost(SelectOneParam param);

    void exportExcelCPRegister(Integer hosrId);
}
