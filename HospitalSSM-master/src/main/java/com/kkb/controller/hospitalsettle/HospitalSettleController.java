package com.kkb.controller.hospitalsettle;

import com.kkb.service.hospitalsettle.HospitalSettleService;
import com.kkb.vo.BaseResp;
import com.kkb.vo.hospitalsettle.req.HospitalSettleReq;
import com.kkb.vo.hospitalsettle.req.UpdateHospitalSettleStatusReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author mj
 * @create 2021-03-23 10:23
 * @describe:
 */
@RestController
@RequestMapping("/settle")
public class HospitalSettleController {

    @Autowired
    private HospitalSettleService hospitalSettleService;

    /**
     * 查询住院结算分页查询
     *
     * @param param
     * @return
     */
    @PostMapping("selectHospitalSettle")
    public BaseResp<Map<String, Object>> selectHospitalSettle(HospitalSettleReq param) {
        try {
            Map<String, Object> map = hospitalSettleService.selectHospitalSettle(param);
            return new BaseResp<>(map);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp<>("1000", false, "查询失败");
        }
    }

    /**
     * 修改项目的结算状态
     *
     * @param param
     * @return
     */
    @PostMapping("updateHospitalSettleStatus")
    public BaseResp<String> updateHospitalSettleStatus(UpdateHospitalSettleStatusReq param) {
        try {
            String tiShi = hospitalSettleService.updateHospitalSettleStatus(param);
            if (StringUtils.isEmpty(tiShi) && tiShi.contains("成功")) {
                return new BaseResp<>(tiShi);
            } else {
                return new BaseResp<>("1000", false, tiShi);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp<>("1000", false, "修改失败");
        }
    }

    /**
     * 导出excel
     *
     * @param ids
     * @return
     */
    @PostMapping("exportExcel")
    public BaseResp<Map<String, Object>> exportExcel(Integer[] ids) {
        try {
            hospitalSettleService.exportExcel(ids);
            return new BaseResp<>();
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp<>("1000", false, "导出失败");
        }
    }

}
