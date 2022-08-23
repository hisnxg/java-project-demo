package com.nxg.controller;

import com.nxg.pojo.Behospital;
import com.nxg.pojo.HosRegister;
import com.nxg.service.HosRegisterService;
import com.nxg.vo.QueryHosRegisterVO;
import com.nxg.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
/**
 * 住院办理控制器
 */
@RequestMapping("/hos")
public class HosRegisterController {
    @Autowired
    private HosRegisterService service;
    @RequestMapping(value = "/add",method = RequestMethod.GET)
    @ResponseBody
    public ResultVO add(HosRegister hosRegister,String deptId,String  d_id, String beH_nursePeople, String beH_patBed, Integer beH_antecedent, String beH_illness){
        //住院表
        Behospital behospital = new Behospital();
        behospital.setBeH_id(hosRegister.getHosR_id());//住院id与病历号相同
        behospital.setBeH_nursePeople(beH_nursePeople);//护理人
        behospital.setBeH_patBed(beH_patBed);//床位号
        behospital.setBeH_antecedent(beH_antecedent);//缴纳押金
        behospital.setBeH_illness(beH_illness);//病情
        hosRegister.setBehospital(behospital);
        hosRegister.setD_id(Integer.valueOf(d_id));

        //护理 床位号 缴纳押金 病情
        //将挂号数据添加到dao
        System.out.println(hosRegister);
        int add = service.add(hosRegister);
        System.out.println(add);
        //
        ResultVO<HosRegister> data = new ResultVO<>();
        data.setCode(200);
        return data;
    }

    /**
     * 查询所有住院人员
     * @return
     */
    @RequestMapping(value = "/select",method = RequestMethod.GET)
    @ResponseBody
    public ResultVO<HosRegister> queryAll(Integer pageNum, Integer pageSize, QueryHosRegisterVO queryHosRegisterVO){
        if(pageNum == null || pageNum <= 0){
            pageNum=1;
        }
        if(pageSize == null || pageSize <=0){
            pageSize=5;
        }
        List<HosRegister> hosRegisters = service.queryAll(pageNum,pageSize,queryHosRegisterVO);
        ResultVO<HosRegister> data = new ResultVO<>();
        data.setList(hosRegisters);
        return data;
    }

    @RequestMapping("queryById")
    @ResponseBody
    public ResultVO<HosRegister> queryById(String hosR_id){
        HosRegister hosRegister = service.queryById(hosR_id);
        ResultVO<HosRegister> data = new ResultVO<>();
        data.setObj(hosRegister);
        return data;
    }
//    @RequestMapping(value = "/list",method = RequestMethod.GET)
//    public ResultVO<Team> queryByPage(Integer pageNum, Integer pageSize, TeamVo teamVo) throws ParseException {
//        System.out.println(teamVo);
//        if(pageNum == null || pageNum <= 0){
//            pageNum=1;
//        }
//        if(pageSize == null || pageSize <=0){
//            pageSize=5;
//        }
//        PageInfo<Team> teamPageInfo = teamService.queryByPage(pageNum, pageSize, teamVo);
//        Data<Team> teamData = new Data<>();
//        teamData.setPageInfo(teamPageInfo);
//        return teamData;
//    }
}
