package com.kaikeba.mapper;

import com.kaikeba.pojo.Behospital;
import com.kaikeba.pojo.BehospitalExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BehospitalMapper {
    long countByExample(BehospitalExample example);

    int deleteByExample(BehospitalExample example);

    int insert(Behospital record);

    int insertSelective(Behospital record);

    List<Behospital> selectByExample(BehospitalExample example);

    int updateByExampleSelective(@Param("record") Behospital record, @Param("example") BehospitalExample example);

    int updateByExample(@Param("record") Behospital record, @Param("example") BehospitalExample example);
}