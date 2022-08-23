package com.kkb.controller.cpregistermanager;

import com.github.pagehelper.PageInfo;
import com.kkb.bean.Behospital;
import com.kkb.bean.Chargeproject;
import com.kkb.bean.Cpregister;
import com.kkb.service.cpregistermanager.CpregisterManagerService;
import com.kkb.vo.BaseResp;
import com.kkb.vo.cpregistermanager.req.AddNewCPParam;
import com.kkb.vo.cpregistermanager.req.SelectCPByNameReq;
import com.kkb.vo.cpregistermanager.req.SelectCpregisterReq;

import com.kkb.vo.cpregistermanager.req.SelectOneParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/hospital")
public class CpregisterManagerController {

    @Autowired
    private CpregisterManagerService cpregisterManagerService;

    @PostMapping("queryCpregister")
    public BaseResp<Map<String,Object>> queryCpregister(SelectCpregisterReq param) {
        if (param.getPageNum()==null){
            param.setPageNum(1);
        }
        PageInfo<Cpregister> cpregisterPage = cpregisterManagerService.queryCpregisterByParam(param);
        Map<String,Object> map = new HashMap<>();
        map.put("cpregisterPage",cpregisterPage);
        map.put("param",param);
        return new BaseResp<>(map);
    }

    /**
     * 通过病历号查询
     * @param param
     * @return
     */
    @PostMapping("queryCpregisterById")
    public BaseResp<Map<String,Object>> selectCpregisterById(SelectOneParam param){
        PageInfo<Cpregister> cpregisterPage = cpregisterManagerService.selectCpregisterById(param);
        Map<String,Object> map = new HashMap<>();
        map.put("cpregisterPage",cpregisterPage);
        map.put("param",param);
        return new BaseResp<>(map);
    }

    /**
     * 通过收费项目名查询
     * @param param
     * @return
     */
    @PostMapping("selectChargeProject")
    public BaseResp<Map<String,Object>> selectChargeProject(SelectCPByNameReq param){
        Chargeproject chargeproject = cpregisterManagerService.selectChargeProject(param);
        Map<String, Object> map = new HashMap<>();
        map.put("chargeproject",chargeproject);
        return new BaseResp<>(map);
    }

    @PostMapping("addNewCP")
    public BaseResp<Map<String,Object>> addNewCP(AddNewCPParam param){
        int addFlag = cpregisterManagerService.insertByParam(param);
        Map<String, Object> map = new HashMap<>();
        map.put("addFlag",addFlag);
        return new BaseResp<>(map);
    }

    @PostMapping("selectBehospitalById")
    public BaseResp<Map<String,Object>> selectBehospitalById(SelectOneParam param){
        Behospital behospital = cpregisterManagerService.selectBehospitalById(param);
        Integer cost = cpregisterManagerService.selectCost(param);
        Map<String, Object> map = new HashMap<>();
        map.put("behospital",behospital);
        map.put("cost",cost);
        return new BaseResp<>(map);
    }

    @GetMapping("exportExcelCPRegister")
    public BaseResp<Map<String,Object>> exportExcelCPRegister(Integer hosrId){
        try {
            cpregisterManagerService.exportExcelCPRegister(hosrId);
            return new BaseResp<>();
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp<>("1002",true,"导出收费项目登记表失败");
        }
    }
}
