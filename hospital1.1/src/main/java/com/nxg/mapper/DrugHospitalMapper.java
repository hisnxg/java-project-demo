package com.nxg.mapper;

import com.nxg.pojo.DrugHospital;
import com.nxg.pojo.DrugHospitalExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
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