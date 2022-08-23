package com.kkb.dao;

import com.kkb.bean.Doctor;
import com.kkb.bean.Hosregister;
import com.kkb.vo.hospitalsettle.resp.HospitalSettleResp;
import com.kkb.vo.hospzation.resp.BeHospitalResp;
import com.kkb.vo.registration.req.BeHosregisterResp;
import com.kkb.vo.registration.req.GetDoctorNameByKeshiParam;
import com.kkb.vo.registration.req.SelectRegistrationReq;
import com.kkb.vo.registration.req.UpdateRegistrationParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HosregisterMapper {
    int deleteByPrimaryKey(Integer hosrId);

    int insert(Hosregister record);

    int insertSelective(Hosregister record);

    Hosregister selectByPrimaryKey(Integer hosrId);

    int updateByPrimaryKeySelective(Hosregister record);

    int updateByPrimaryKey(Hosregister record);

    List<Hosregister> queryHosregisterByParam(SelectRegistrationReq param);

    Hosregister FindByHosrIdParam(Integer hosrId);

    List<Doctor> findAllKeshi();

    String updateById(Integer[] hosrIds);

    /**
     * 查询 Hosregister
     * @param id 根据ID查询
     * @return
     */
    Hosregister selectHosregister(Integer id);

//    void updateHosregister(Hosregister hosregister);

    List<Doctor> getDoctorNameByKeshi(GetDoctorNameByKeshiParam param);

    Doctor findByDoctorId(Integer dId);

    int updateRegistration(UpdateRegistrationParam param);

    List<BeHosregisterResp> selectHosregisterIds(@Param("ids") Integer[] ids);

}