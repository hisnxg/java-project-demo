package com.kkb.service.Drugpeople;

import com.github.pagehelper.PageInfo;
import com.kkb.bean.Drugpeople;
import com.kkb.vo.Drugpeople.req.SelectDrugpeopleReq;

import java.util.Map;

/**
 * @author sufenglong
 * @date 2021/3/24 22:22
 * @describe:
 */
public interface DrugpeopleService {


    void addSendDrug(Drugpeople drugpeople);

    PageInfo<Drugpeople> selectDrugPeopleByParam(SelectDrugpeopleReq param);

    Map<String,Object> selectDrugPeopleById(Integer peopleId,Integer num);//详情

    void updateSendDrug30(Drugpeople drugpeople);

    void updateSendDrug1(Drugpeople drugpeople);

    void updateSendDrug5(Drugpeople drugpeople);

    void updateSendDrugAll(Drugpeople drugpeople);

    void updateSendDrug(Drugpeople drugpeople);

    Drugpeople selectByPrimaryKey(Integer peopleId);

    Drugpeople selectByHosrIdAndDrugId(Integer peopleId, Integer drugId);
}
