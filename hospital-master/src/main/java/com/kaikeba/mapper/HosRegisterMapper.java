package com.kaikeba.mapper;

import com.kaikeba.pojo.HosRegister;
import com.kaikeba.pojo.HosRegisterExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HosRegisterMapper {
    long countByExample(HosRegisterExample example);

    int deleteByExample(HosRegisterExample example);

    int insert(HosRegister record);

    int insertSelective(HosRegister record);

    List<HosRegister> selectByExample(HosRegisterExample example);

    int updateByExampleSelective(@Param("record") HosRegister record, @Param("example") HosRegisterExample example);

    int updateByExample(@Param("record") HosRegister record, @Param("example") HosRegisterExample example);
}