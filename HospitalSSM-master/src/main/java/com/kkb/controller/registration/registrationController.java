package com.kkb.controller.registration;


import com.github.pagehelper.PageInfo;
import com.kkb.bean.Doctor;
import com.kkb.bean.Hosregister;
import com.kkb.service.hospitalsettle.HospitalSettleService;
import com.kkb.service.registration.RegistrationService;
import com.kkb.vo.BaseResp;
import com.kkb.vo.registration.req.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/registration")
public class registrationController {

    @Autowired
    private RegistrationService registrationService;

    @GetMapping("queryRegistration")
    public BaseResp<Map<String,Object>> queryRegistration(SelectRegistrationReq param){
        if (param.getPageNum()==null){
            param.setPageNum(1);
        }
        PageInfo<Hosregister> hosregisterpage = registrationService.queryHosregisterByParam(param);
        Map<String,Object> map = new HashMap<>();
        map.put("hosregisterpage",hosregisterpage);
        map.put("param",param);
        return new BaseResp<>(map);
    }

    //添加挂号信息
    @PostMapping("addRegistration")
    public BaseResp<Map<String,Object>> addRegistration(AddRegistrationParam param){
        try {
            registrationService.addHosregister(param);
        }catch (Exception e){
            e.printStackTrace();
            return new BaseResp<>("1000",true,"添加失败");
        }
        return new BaseResp<>();
    }

  /*  @PostMapping("updateRegistration")
    public BaseResp<String> updateRegistration(UpdateRegistrationReq param){
        try {
            registrationService.updateRegistration(param);
            return new BaseResp<>();
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp<>("1003",false,"修改失败");
        }
    }*/

   /* @PostMapping("deleteRegistration")
    public BaseResp<Map<String,Object>> deleteRegistration(Integer[] hospIds){
        try {
            registrationService.deleteRegistration(hospIds);
            return new BaseResp<>();
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp<>("1005",false,"删除失败");
        }
    }
*/
    @PostMapping("findByHosrId")
    public BaseResp<Map<String,Object>> findByHosrId(FindByHosrIdParam param){
        Hosregister hosregister = registrationService.FindByHosrIdParam(param);
        Map<String,Object> map = new HashMap<>();
        map.put("hosregister",hosregister);
        map.put("param",param);
        return new BaseResp<>(map);
    }

    @GetMapping(value = "/updateTest")
    public BaseResp<String> updateTest(Integer[] hosrId){
        try {
            String s = registrationService.updateById(hosrId);
            if (StringUtils.isEmpty(s)){
                return new BaseResp<>();
            }else {
                return new BaseResp<>("10002",false,s);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp<>("1009",false,"退号失败");
        }

    }


    /**
     * 以下内容废弃，写重了
     * @param
     * @return
     */
  /*  @PostMapping(value = "/getHsp",produces = "text/json;charset=UTF-8")
    public String test1(Integer id){
        Hosregister hosregister = registrationService.selectHosregister(id);
        String s = JSON.toJSONString(hosregister);
        return s;
    }

    @PostMapping(value = "/getHHH",produces = "text/json;charset=UTF-8")
    public Hosregister test2(Integer hosrId, String hosrIdcar, String hosrMedical, BigDecimal hosrRegprice,
                             String hosrPhone, Integer hosrSelfprice, Integer hosrSex, Integer hosrAge, String hosrWork,
                             Integer hosrLookdoctor, String hosrRemark, String hosrName){
        Hosregister hosregister = new Hosregister();
        registrationService.updateHosregister(hosregister);
        return hosregister;
    }*/


    @GetMapping("findallkeshi")
    public BaseResp<Map<String,Object>> findAllKeshi(){
        List<Doctor> allKeshi = registrationService.findAllKeshi();
        Map<String,Object> map = new HashMap<>();
        map.put("allKeshi",allKeshi);
        return new BaseResp<>(map);
    }

    @GetMapping("getDoctorNameByKeshi")
    public BaseResp<Map<String,Object>> getDoctorNameByKeshi(GetDoctorNameByKeshiParam param){
        //TODO
        List<Doctor> list = registrationService.getDoctorNameByKeshi(param);
        Map<String,Object> map = new HashMap<>();
        map.put("list",list);
        return new BaseResp<>(map);
    }

    @PostMapping("findByDoctorId")
    public BaseResp<Map<String,Object>> findByDoctorId(Integer dId){
        Doctor doctor = registrationService.findByDoctorId(dId);
        Map<String, Object> map = new HashMap<>();
        map.put("doctor",doctor);
        return new BaseResp<>(map);
    }

    @PostMapping("updateRegistration")
    public BaseResp<Map<String,Object>> updateRegistration(UpdateRegistrationParam param){
        try {
            registrationService.updateRegistration(param);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp<>("1008",false,"修改失败");
        }
        return new BaseResp<>();
    }

    /**
     * 导出excel文件
     * @param id
     * @return
     */
    @GetMapping("/exportExcel")
    public BaseResp<String> exportExcel(Integer[] id){
        try {
            registrationService.exportExcel(id);
            return new BaseResp<>();
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp<>("1000",false,"导出失败");
        }
    }



}
