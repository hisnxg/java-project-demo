package com.kkb.service.doctormanager;

import com.github.pagehelper.PageInfo;
import com.kkb.bean.Doctor;
import com.kkb.vo.doctormanager.req.AddDoctorParam;
import com.kkb.vo.doctormanager.req.SelectDoctorReq;
import com.kkb.vo.doctormanager.req.SelectOneParam;
import com.kkb.vo.doctormanager.req.UpdateDoctorReq;

import java.util.List;
import java.util.Map;

/**
 * @author mj
 * @create 2021-03-18 18:34
 * @describe:
 */
public interface DoctorManagerService {

    PageInfo<Doctor> queryDoctorByParam(SelectDoctorReq param);

    void addDoctor(AddDoctorParam param);

    Map<String, Object> selectDoctorByName(SelectOneParam param);

    void updateDoctor(UpdateDoctorReq param);

    void exportExcelDoctor(List<Integer> doctorIds);

    void deleteDoctorById(Integer doctorIds);

    void deleteDoctorByIds(Integer[] doctorIds);

    Map<String, Object> selectDoctorByKeshi(String keshi);
}
