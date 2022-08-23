package com.kkb.service.registration;

import com.github.pagehelper.PageInfo;
import com.kkb.bean.Doctor;
import com.kkb.bean.Hosregister;
import com.kkb.vo.registration.req.*;

import java.util.List;


public interface RegistrationService {

    //参数查询挂号信息
    PageInfo<Hosregister> queryHosregisterByParam(SelectRegistrationReq param);

    void addHosregister(AddRegistrationParam param);

    void updateRegistration(UpdateRegistrationParam param);

    String updateById(Integer[] hosrIds);

    Hosregister FindByHosrIdParam(FindByHosrIdParam param);

    List<Doctor> findAllKeshi();

    List<Doctor> getDoctorNameByKeshi(GetDoctorNameByKeshiParam param);

    Doctor findByDoctorId(Integer dId);

    void exportExcel(Integer[] id);


}
