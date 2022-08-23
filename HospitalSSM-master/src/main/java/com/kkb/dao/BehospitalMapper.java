package com.kkb.dao;

import com.kkb.bean.Behospital;
import com.kkb.vo.hospitalsettle.req.HospitalSettleReq;
import com.kkb.vo.hospitalsettle.resp.HospitalSettleResp;
import com.kkb.vo.hospzation.req.BeHospitalReq;
import com.kkb.vo.hospzation.resp.BeHospitalResp;
import com.kkb.vo.hospzation.resp.BeHospitalTotal;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BehospitalMapper {
    int deleteByPrimaryKey(Integer behId);

    int insert(Behospital record);

    int insertSelective(Behospital record);

    Behospital selectByPrimaryKey(Integer behId);

    int updateByPrimaryKeySelective(Behospital record);

    int updateByPrimaryKey(Behospital record);

    List<BeHospitalResp> selectBeHospital(BeHospitalReq param);

    BeHospitalTotal selectBehStateById(Integer hosrId);

    List<HospitalSettleResp> selectHospitalSettle(HospitalSettleReq param);

    List<BeHospitalResp> selectBeHospitalByIds(@Param("ids") Integer[] ids);

    List<HospitalSettleResp> exportExcel(@Param("ids") Integer[] ids);
}