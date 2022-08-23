package com.kkb.dao;

import com.kkb.bean.Doctor;
import com.kkb.vo.doctormanager.req.SelectDoctorReq;
import com.kkb.vo.doctormanager.req.SelectOneParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DoctorMapper {

    int deleteByPrimaryKey(Integer dId);

    int insert(Doctor record);

    int insertSelective(Doctor record);

    Doctor selectByPrimaryKey(Integer dId);

    int updateByPrimaryKeySelective(Doctor record);

    int updateByPrimaryKey(Doctor record);

    List<Doctor> selectDoctorByParam(SelectDoctorReq param);

    Doctor selectDoctorByName(SelectOneParam param);

    List<Doctor> selectDocterByIds(@Param("doctorIds") List<Integer> doctorIds);

    void deleteDoctorByIds(@Param("doctorIds") List<Integer> doctorIds);

    List<Doctor> selectDoctorByKeshi(String keshi);
}