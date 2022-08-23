package com.kkb.controller.behospital;

import com.kkb.bean.Behospital;
import com.kkb.service.behospital.BeHospitalService;
import com.kkb.vo.BaseResp;
import com.kkb.vo.hospzation.req.BeHospitalReq;
import com.kkb.vo.hospzation.req.BeHospitalTotalReq;
import com.kkb.vo.hospzation.req.UpdateBeHospitalPriceReq;
import com.kkb.vo.hospzation.req.UpdateBehStateStatusReq;
import com.kkb.vo.hospzation.resp.BeHospitalChargeResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author mj
 * @create 2021-03-22 19:19
 * @describe: 住院办理
 */
@RestController
@RequestMapping("/hospital")
public class BeHospitalController {

    @Autowired
    private BeHospitalService beHospitalService;

    /**
     * 根据参数查询住院信息
     * @param param
     * @return
     */
    @PostMapping("/selectBeHospital")
    public BaseResp<Map<String,Object>> selectBeHospital(BeHospitalReq param){
        try {
            Map<String,Object> map = beHospitalService.selectBeHospital(param);
            return new BaseResp<>(map);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp<>("1001",false,"查询失败");
        }
    }

    /**
     * 添加住院信息
     * @param param
     * @return
     */
    @PostMapping("/addBeHospital")
    public BaseResp<String> addBeHospital(Behospital param){
        try {
            beHospitalService.addBeHospital(param);
            return new BaseResp<>();
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp<>("1002",false,"添加失败");
        }
    }

    /**
     * 修改挂号状态
     * @param param
     * @return
     */
    @GetMapping("/updateBehStateStatus")
    public BaseResp<String> updateBehStateStatus(UpdateBehStateStatusReq param){
        try {
            String tishi = beHospitalService.updateBehStateStatus(param);
            if (StringUtils.isEmpty(tishi)) {
                return new BaseResp<>();
            }else {
                return new BaseResp<>("1003",false,tishi);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp<>("1003",false,"修改失败");
        }
    }

    /**
     * 根据挂号id，查询住院信息
     * @param hosrId
     * @return
     */
    @GetMapping("/selectBeHospitalStateById")
    public BaseResp<Map<String,Object>> selectBeHospitalStateById(Integer hosrId){
        try {
            Map<String,Object> map = beHospitalService.selectBeHospitalStateById(hosrId);
            return new BaseResp<>(map);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp<>("1001",false,"查询失败");
        }
    }

    /**
     * 缴纳押金
     * @param param
     * @return
     */
    @GetMapping("/updateBeHospitalPrice")
    public BaseResp<Map<String,Object>> updateBeHospitalPrice(UpdateBeHospitalPriceReq param){
        try {
            beHospitalService.updateBeHospitalPrice(param);
            return new BaseResp<>();
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp<>("1004",false,"修改押金失败");
        }
    }

    /**
     * 删除住院信息
     * @param hospIds
     * @return
     */
    @GetMapping("/deleteBeHospital")
    public BaseResp<Map<String,Object>> deleteBeHospital(Integer[] hospIds){
        try {
            beHospitalService.deleteBeHospital(hospIds);
            return new BaseResp<>();
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp<>("1005",false,"删除失败");
        }
    }

    /**
     * 修改住院信息
     * @param param
     * @return
     */
    @PostMapping("/updateBeHospital")
    public BaseResp<Map<String,Object>> updateBeHospital(BeHospitalTotalReq param){
        try {
            beHospitalService.updateBeHospital(param);
            return new BaseResp<>();
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp<>("1005",false,"修改失败");
        }
    }

    /**
     * 查询收费项目,计算余额
     * @param id
     * @return
     */
    @GetMapping("/selectPrice")
    public BaseResp<List<BeHospitalChargeResp>> selectPrice(Integer id){
        try {
            List<BeHospitalChargeResp> list = beHospitalService.selectPrice(id);
            return new BaseResp<>(list);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp<>("1000",false,"查询失败");
        }
    }

    /**
     * 导出excel文件
     * @param id
     * @return
     */
    @GetMapping("/exportExcel")
    public BaseResp<String> exportExcel(Integer[] id){
        try {
            beHospitalService.exportExcel(id);
            return new BaseResp<>();
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp<>("1000",false,"导出失败");
        }
    }
}
