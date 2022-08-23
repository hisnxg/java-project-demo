package com.kkb.dao;

import com.kkb.bean.Drugpeople;
import com.kkb.vo.Drugpeople.req.SelectDrugpeopleReq;
import com.kkb.vo.Drugpeople.req.UpdateDrugePeopleReq;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface DrugpeopleMapper {

    int deleteByPrimaryKey(Integer drId);

    int insert(Drugpeople record);

    int insertSelective(Drugpeople record);

    Drugpeople selectByPrimaryKey(Integer drId);

    int updateByPrimaryKeySelective(UpdateDrugePeopleReq record);

    int updateByPrimaryKey(Drugpeople record);


    List<Drugpeople> selectDrugPeopleById(@Param("peopleId") Integer peopleId);//详情


    List<Drugpeople> selectDrugPeopleByParam(SelectDrugpeopleReq param);


    Drugpeople selectNumber(Integer id);


//    新的方法

    void addSendDrug(Drugpeople drugpeople);

    List<Drugpeople> getgetSendDrugList();

    List<Drugpeople> getSendDrugListByRId(Integer rId);

    //发药方法
    void updateSendDrug(Drugpeople drugpeople);

    Drugpeople getByDreId(Integer dreId);


    Drugpeople selectChongFu(Map<String, Object> map);

}