package com.nxg.mapper;

import com.nxg.pojo.HosRegister;
import com.nxg.pojo.HosRegisterExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface HosRegisterMapper {
    long countByExample(HosRegisterExample example);

    int deleteByExample(HosRegisterExample example);

    int insert(HosRegister record);

    int insertSelective(HosRegister record);

    List<HosRegister> selectByExample(HosRegisterExample example);

    int updateByExampleSelective(@Param("record") HosRegister record, @Param("example") HosRegisterExample example);

    int updateByExample(@Param("record") HosRegister record, @Param("example") HosRegisterExample example);
}