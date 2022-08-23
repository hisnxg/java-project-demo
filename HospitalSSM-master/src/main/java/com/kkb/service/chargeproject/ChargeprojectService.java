package com.kkb.service.chargeproject;

import com.github.pagehelper.PageInfo;
import com.kkb.bean.Chargeproject;
import com.kkb.vo.chargeproject.req.AddChargeprojectParam;
import com.kkb.vo.chargeproject.req.ChaChongParam;
import com.kkb.vo.chargeproject.req.SelectChergeproject;

public interface ChargeprojectService {


    void addChargeproject(AddChargeprojectParam param);

    PageInfo<Chargeproject> selectChargeproject(SelectChergeproject param);

    Chargeproject selectByChapName(ChaChongParam param);

    Chargeproject selectByChapId(Chargeproject chapId);


    void updateByPrimaryKeySelective(Chargeproject chapId);

    void deleteById(Integer chapId);

}
