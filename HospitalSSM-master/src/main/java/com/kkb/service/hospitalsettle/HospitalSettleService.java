package com.kkb.service.hospitalsettle;

import com.kkb.vo.hospitalsettle.req.HospitalSettleReq;
import com.kkb.vo.hospitalsettle.req.UpdateHospitalSettleStatusReq;

import java.util.Map;

/**
 * @author mj
 * @create 2021-03-23 10:38
 * @describe:
 */
public interface HospitalSettleService {
    Map<String, Object> selectHospitalSettle(HospitalSettleReq param);

    String updateHospitalSettleStatus(UpdateHospitalSettleStatusReq param);

    void exportExcel(Integer[] ids);
}
