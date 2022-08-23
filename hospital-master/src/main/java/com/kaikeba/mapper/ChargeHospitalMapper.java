package com.kaikeba.mapper;

import com.kaikeba.pojo.ChargeHospital;
import com.kaikeba.pojo.ChargeHospitalExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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