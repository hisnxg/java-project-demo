package com.nxg.mapper;

import com.nxg.pojo.Behospital;
import com.nxg.pojo.BehospitalExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BehospitalMapper {
    long countByExample(BehospitalExample example);

    int deleteByExample(BehospitalExample example);

    int insert(Behospital record);

    int insertSelective(Behospital record);

    List<Behospital> selectByExample(BehospitalExample example);

    int updateByExampleSelective(@Param("record") Behospital record, @Param("example") BehospitalExample example);

    int updateByExample(@Param("record") Behospital record, @Param("example") BehospitalExample example);
}