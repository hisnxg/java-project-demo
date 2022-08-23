package com.kaikeba.controller;

import com.github.pagehelper.PageInfo;
import com.kaikeba.pojo.Charge;
import com.kaikeba.service.ChargeService;
import com.kaikeba.vo.QueryChargeVO;
import com.kaikeba.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * @author msk
 * @Description  收费管理管理的控制器
 */
@Controller
@RequestMapping("charge")
@ResponseBody
public class ChargeController {
    @Autowired
    private ChargeService chargeService;

    /**
     * 多条件分页查询收费项目（检查收费项目登记页面使用）
     * @param pageSize
     * @param pageNum
     * @return
     */
    @RequestMapping(value = "list",method = RequestMethod.GET)
    public ModelAndView queryByPage(Integer id, Integer pageNum, Integer pageSize ){
        if (pageNum==null||pageNum<=0){
            pageNum=1;
        }
        if (pageSize==null||pageSize<=0){
            pageSize=5;
        }
        ModelAndView mv=new ModelAndView();
        Map<String, Object> map = chargeService.queryByPage(id,pageNum,pageSize);
        mv.addObject("id",id);
        mv.addObject("pageInfo",map.get("pageInfo"));
        mv.addObject("hospital",map.get("hospital"));
        mv.addObject("charges",map.get("charges"));
        mv.setViewName("hospital/account-look2");
        return mv;
    }

    /**
     * 多条件分页查询收费项目信息的包装，收费项目管理页面使用
     * @param vo
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "list2",method = RequestMethod.GET)
    public ResultVO<Charge> queryByPage2(QueryChargeVO vo,Integer pageNum,Integer pageSize){
        if (pageNum==null||pageNum<=0){
            pageNum=1;
        }
        if (pageSize==null||pageSize<=0){
            pageSize=5;
        }
        PageInfo<Charge> pageInfo = chargeService.queryByPage2(pageNum, pageSize, vo);
        return new ResultVO<>(pageInfo);
    }

    /**
     * 添加收费项目
     * @param id
     * @return
     */
    @RequestMapping(value = "addAdvance",method = RequestMethod.GET)
    public ModelAndView addAdvance(Integer id){
        ModelAndView mv=new ModelAndView();
        List list = chargeService.addAdvance(id);
        mv.addObject("hospital",list.get(0));
        mv.addObject("register",list.get(1));
        mv.setViewName("hospital/charge-new");
        return mv;
    }

    /**
     * 根据收费项目名称查询收费项目信息
     * @param chargeName
     * @return
     */
    @RequestMapping(value = "queryByName",method = RequestMethod.GET)
    public Charge queryByName(String chargeName){
        Charge charge = chargeService.queryByName(chargeName);
        return charge;
    }

    /**
     * 添加收费项目信息
     * @param hospitalId
     * @param charge
     * @return
     */
    @RequestMapping(value = "add",method = RequestMethod.POST)
    public ResultVO addCharge(Integer hospitalId,Charge charge){
        Integer integer = chargeService.addCharge(hospitalId, charge);
        if (integer==2){
            return new ResultVO();
        }
        return new ResultVO(500,"内部服务器异常，请稍后再试！");
    }

    /**
     * 根据主键查询收费项目信息
     * @param id
     * @return
     */
    @RequestMapping(value = "info",method = RequestMethod.GET)
    public ModelAndView info(Integer id){
        ModelAndView mv=new ModelAndView();
        Charge charge = chargeService.queryById(id);
        mv.addObject("charge",charge);
        mv.setViewName("hospital/charge-edit2");
        return mv;
    }
    /**
     * 根据主键查询收费项目信息(更新前的准备工作）
     * @param id
     * @return
     */
    @RequestMapping(value = "updateAdvance",method = RequestMethod.GET)
    public ModelAndView updateAdvance(Integer id){
        ModelAndView mv=new ModelAndView();
        Charge charge = chargeService.queryById(id);
        mv.addObject("charge",charge);
        mv.setViewName("hospital/charge-edit1");
        return mv;
    }

    /**
     * 更新收费项目信息
     * @param id
     * @param charge
     * @return
     */
    @RequestMapping(value = "{id}",method = RequestMethod.PUT)
    public ResultVO<Charge> updateCharge(@PathVariable("id")Integer id,Charge charge){
        charge.setId(id);
        int i = chargeService.updateCharge(charge);
        if (i==1){
            return new ResultVO<>();
        }
        return new ResultVO<>(500,"内部服务器错误，请稍后再试！");
    }

    /**
     * 根据主键删除收费项目信息
     * @param id
     * @return
     */
    @RequestMapping(value = "{id}",method = RequestMethod.DELETE)
    public ResultVO<Charge> deleteCharge(@PathVariable("id") Integer id){
        int i = chargeService.deleteCharge(id);
        if (i==1){
            return new ResultVO<>();
        }
        return new ResultVO<>(500,"内部服务器错误，请稍后再试！");
    }
}
