package com.kaikeba.mapper;

import com.kaikeba.pojo.DrugHospital;
import com.kaikeba.pojo.DrugHospitalExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DrugHospitalMapper {
    long countByExample(DrugHospitalExample example);

    int deleteByExample(DrugHospitalExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DrugHospital record);

    int insertSelective(DrugHospital record);

    List<DrugHospital> selectByExample(DrugHospitalExample example);

    DrugHospital selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DrugHospital record, @Param("example") DrugHospitalExample example);

    int updateByExample(@Param("record") DrugHospital record, @Param("example") DrugHospitalExample example);

    int updateByPrimaryKeySelective(DrugHospital record);

    int updateByPrimaryKey(DrugHospital record);
}