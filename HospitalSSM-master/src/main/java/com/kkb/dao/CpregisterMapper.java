package com.kkb.dao;

import com.kkb.bean.Chargeproject;
import com.kkb.bean.Cpregister;
import com.kkb.vo.cpregistermanager.req.AddNewCPParam;
import com.kkb.vo.cpregistermanager.req.SelectCPByNameReq;
import com.kkb.vo.cpregistermanager.req.SelectCpregisterReq;

import java.util.List;

public interface CpregisterMapper {
    int deleteByPrimaryKey(Integer cprId);

    int insert(Cpregister record);

    int insertSelective(Cpregister record);

    Cpregister selectByPrimaryKey(Integer cprId);

    int updateByPrimaryKeySelective(Cpregister record);

    int updateByPrimaryKey(Cpregister record);

    List<Cpregister> selectCpregisterByParam(SelectCpregisterReq param);

    List<Cpregister> selectCpregisterById(Integer hosrId);

    Chargeproject selectChargeProject(SelectCPByNameReq param);

    int insertByParam(AddNewCPParam param);

    Integer selectCost(Integer hosrId);
}