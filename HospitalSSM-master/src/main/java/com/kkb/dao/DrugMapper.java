package com.kkb.dao;

import com.kkb.bean.Drug;
import com.kkb.vo.drugmanager.req.AddDrugNumberParam;
import com.kkb.vo.drugmanager.req.SelectDrugReq;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DrugMapper {

    int deleteByPrimaryKey(Integer drId);

    int insert(Drug record);

    int insertSelective(Drug record);

    Drug selectByPrimaryKey(Integer drId);

    int updateByPrimaryKeySelective(Drug record);

    int updateByPrimaryKey(Drug record);

    List<Drug> selectDrugByParam(SelectDrugReq param);

    int updateNumberByPrimaryKey(AddDrugNumberParam param);

    List<Drug> selectDrugByIds(@Param("drugIds") List<Integer> drugIds);

    //sfl 添加
//    int updateNumberByPrimaryKey(Drug drug);

}