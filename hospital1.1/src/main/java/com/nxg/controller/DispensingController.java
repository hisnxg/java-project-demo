package com.nxg.controller;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageInfo;
import com.nxg.pojo.Drug;
import com.nxg.pojo.DrugHospital;
import com.nxg.pojo.Hospital;
import com.nxg.service.DispensingService;
import com.nxg.vo.ResultVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Controller
@ResponseBody
@RequestMapping("dispensing")
public class DispensingController {
    @Resource
    DispensingService dispensingService;

    @RequestMapping(value = "list",method = RequestMethod.GET)
    public ResultVO<Hospital> queryByPage(@RequestParam(defaultValue ="0") Integer hid,@RequestParam(defaultValue = "1")Integer pageNum, @RequestParam(defaultValue="5")Integer pageSize){
        PageInfo<Hospital> info = dispensingService.queryByPage(pageNum, pageSize, hid);
        return new ResultVO<>(info);
    }

    @RequestMapping(value = "{hid}",method = RequestMethod.GET)
    public ResultVO<Drug> queryById(@PathVariable("hid") Integer hid){
        List<Drug> drugs = dispensingService.queryById(hid);
        return new ResultVO<>(drugs);

    }

    @RequestMapping(value ="give/{hid}",method = RequestMethod.PUT)
    public ResultVO<Drug> sendDrug(@PathVariable("hid") Integer hid, Integer drugId, Integer number){
        Boolean flag = dispensingService.sendDrug(hid, drugId, number);
        if(flag){
            return new ResultVO<>();
        }else{
            return new ResultVO<>(500,"您输入的发药数量有误或服务器内部异常，请稍后再试！");
        }
    }

    @RequestMapping(value ="drugs",method = RequestMethod.GET)
    public ResultVO<Drug> queryAllDrugs(){
        List<Drug> drugs = dispensingService.queryAllDrugs();
        return new ResultVO<>(drugs);
    }

    @RequestMapping(value = "drugs/{drugId}",method=RequestMethod.POST)
    public ResultVO<Drug> giveDrugs(@PathVariable("drugId")Integer drugId, String hids, Integer number){
        //获取所有病人的id
        JSONArray arr = JSONArray.parseArray(hids);
        ArrayList<Integer> list = new ArrayList<>();
        for (Object o : arr) {
            String s = o.toString();
            System.out.println("病人的编号:"+s);
            list.add(Integer.parseInt(s));
        }
        Boolean res = dispensingService.giveDrugs(drugId, list, number);
        if(res){
            return new ResultVO<>();
        }else{
            return new ResultVO<>(500,"药品库存不足或服务器出现异常，请稍候重试");
        }

    }

    @RequestMapping(value = "look/{hid}",method=RequestMethod.GET)
    public ResultVO<DrugHospital> lookDrugs(@PathVariable("hid")Integer hid,@RequestParam(defaultValue = "1")Integer pageNum, @RequestParam(defaultValue="5")Integer pageSize){
        PageInfo<DrugHospital> info = dispensingService.lookDrugs(hid, pageNum, pageSize);
        return new ResultVO<>(info);
    }
    @RequestMapping(value = "grant/{hid}",method=RequestMethod.PUT)
    public ResultVO<DrugHospital> grantDrugs(@PathVariable("hid")Integer hid, Integer drugId, Integer type){
        boolean flag = dispensingService.grantDrugs(hid, drugId, type);
        if(flag){
            return new ResultVO<>();
        }else{
            return new ResultVO<>(500,"发药数量有误或服务器异常，请稍后重试");
        }
    }
}


