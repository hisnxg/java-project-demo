package com.kkb.controller.doctormanager;

import com.github.pagehelper.PageInfo;
import com.kkb.bean.Doctor;
import com.kkb.service.doctormanager.DoctorManagerService;
import com.kkb.vo.BaseResp;
import com.kkb.vo.doctormanager.req.AddDoctorParam;
import com.kkb.vo.doctormanager.req.SelectDoctorReq;
import com.kkb.vo.doctormanager.req.SelectOneParam;
import com.kkb.vo.doctormanager.req.UpdateDoctorReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author mj
 * @create 2021-03-18 18:18
 * @describe:
 */
@RestController
@RequestMapping("/doctor")
public class DoctorManagerController {

    @Autowired
    private DoctorManagerService doctorManagerService;

    /**
     * 根据参数查询医生信息
     * @param param
     * @return
     */
    @PostMapping("queryDoctor")
    public BaseResp<Map<String,Object>> queryDoctor(SelectDoctorReq param){
        PageInfo<Doctor> doctorPage = doctorManagerService.queryDoctorByParam(param);

        Map<String,Object> map = new HashMap<>();
        map.put("doctorPage",doctorPage);
        map.put("param",param);

        return new BaseResp<>(map);
    }

    /**
     * 添加医生信息
     * @param param
     * @return
     */
    @PostMapping("addDoctor")
    public BaseResp<Map<String,Object>> addDoctor( AddDoctorParam param){
        try {
            doctorManagerService.addDoctor(param);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp<>("1000",false,"添加失败");
        }
        return new BaseResp<>();
    }

    /**
     * 根据参数查询医生信息
     * @param param
     * @return
     */
    @GetMapping("queryDoctorByName")
    public BaseResp<Map<String,Object>> selectDoctorByName(SelectOneParam param){
        try {
            Map<String,Object> map = doctorManagerService.selectDoctorByName(param);
            return new BaseResp<>(map);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp<>("1001",false,"根据姓名查询医生失败");
        }
    }

    /**
     * 修改医生信息
     * @param param
     * @return
     */
    @PostMapping("updateDoctor")
    public BaseResp<String> updateDoctor(UpdateDoctorReq param){
        try {
            doctorManagerService.updateDoctor(param);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp<>("1002",false,"修改失败");
        }
        return new BaseResp<>();
    }

    /**
     * 导出excel
     * @param doctorIds
     * @return
     */
    @GetMapping("exportExcelDoctor")
    public BaseResp<String> exportExcelDoctor(Integer[] doctorIds){
        try {
            doctorManagerService.exportExcelDoctor(Arrays.asList(doctorIds));
            return new BaseResp<>();
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp<>("1002",true,"医生修改失败");
        }
    }

    /**
     * 根据id单个删除
     * @param doctorId
     * @return
     */
    @GetMapping("deleteDoctorById")
    public BaseResp<String> deleteDoctorById(Integer doctorId){
        try {
            doctorManagerService.deleteDoctorById(doctorId);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp<>("1003",false,"医生删除失败");
        }
        return new BaseResp<>();
    }

    /**
     * 根据id批量删除
     * @param doctorIds
     * @return
     */
    @GetMapping("deleteDoctorByIds")
    public BaseResp<String> deleteDoctorByIds(Integer[] doctorIds){
        try {
            doctorManagerService.deleteDoctorByIds(doctorIds);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp<>("1003",false,"医生修改失败");
        }
        return new BaseResp<>();
    }

    /**
     * 根据科室查询医生
     * @param keshi
     * @return
     */
    @GetMapping("selectDoctorByKeshi")
    public BaseResp<Map<String,Object>> selectDoctorByKeshi(String keshi){
        try {
            Map<String,Object> doctorMap = doctorManagerService.selectDoctorByKeshi(keshi);
            return new BaseResp<>(doctorMap);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp<>("1003",false,"医生修改失败");
        }
    }
}
