package com.nxg.mapper;

import com.nxg.pojo.ChargeHospital;
import com.nxg.pojo.ChargeHospitalExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ChargeHospitalMapper {
    long countByExample(ChargeHospitalExample example);

    int deleteByExample(ChargeHospitalExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ChargeHospital record);

    int insertSelective(ChargeHospital record);

    List<ChargeHospital> selectByExample(ChargeHospitalExample example);

    ChargeHospital selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ChargeHospital record, @Param("example") ChargeHospitalExample example);

    int updateByExample(@Param("record") ChargeHospital record, @Param("example") ChargeHospitalExample example);

    int updateByPrimaryKeySelective(ChargeHospital record);

    int updateByPrimaryKey(ChargeHospital record);
}