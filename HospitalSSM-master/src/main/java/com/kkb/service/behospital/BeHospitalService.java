package com.kkb.service.behospital;

import com.kkb.bean.Behospital;
import com.kkb.vo.hospzation.req.BeHospitalReq;
import com.kkb.vo.hospzation.req.BeHospitalTotalReq;
import com.kkb.vo.hospzation.req.UpdateBeHospitalPriceReq;
import com.kkb.vo.hospzation.req.UpdateBehStateStatusReq;
import com.kkb.vo.hospzation.resp.BeHospitalChargeResp;

import java.util.List;
import java.util.Map;

/**
 * @author mj
 * @create 2021-03-22 23:52
 * @describe:
 */
public interface BeHospitalService {
    Map<String, Object> selectBeHospital(BeHospitalReq param);

    void addBeHospital(Behospital param);

    String updateBehStateStatus(UpdateBehStateStatusReq param);

    Map<String, Object> selectBeHospitalStateById(Integer hosrId);

    void updateBeHospitalPrice(UpdateBeHospitalPriceReq param);

    void deleteBeHospital(Integer[] hospIds);

    void updateBeHospital(BeHospitalTotalReq param);

    List<BeHospitalChargeResp> selectPrice(Integer id);

    void exportExcel(Integer[] id);
}
