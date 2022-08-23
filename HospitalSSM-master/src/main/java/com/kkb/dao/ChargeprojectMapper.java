package com.kkb.dao;

import com.kkb.bean.Chargeproject;
import com.kkb.vo.chargeproject.req.ChaChongParam;
import com.kkb.vo.chargeproject.req.SelectChergeproject;
import com.kkb.vo.hospzation.resp.BeHospitalChargeResp;

import java.util.List;

public interface ChargeprojectMapper {


    int deleteByPrimaryKey(Integer chapId);

    int insert(Chargeproject record);

    /**
     *添加
     * @param record
     * @return
     */
    int insertSelective(Chargeproject record);

    /**
     * 查询
     * @param param
     * @return
     */
    List<Chargeproject> selectByPrimaryKey(SelectChergeproject param);

    /**
     * 根据收费名称查询
     * @param param
     * @return
     */
    Chargeproject selectByChapName(ChaChongParam param);


    /**
     * 修改
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(Chargeproject record);

    /**
     * 修改数据回显
     * @param chapId
     */
    Chargeproject selectByChapId(Chargeproject chapId);


    int updateByPrimaryKey(Chargeproject record);

    List<BeHospitalChargeResp> selectPrice(Integer id);
}