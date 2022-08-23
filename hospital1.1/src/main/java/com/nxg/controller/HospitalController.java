package com.nxg.controller;

import com.github.pagehelper.PageInfo;
import com.nxg.pojo.Hospital;
import com.nxg.service.HospitalService;
import com.nxg.vo.QueryHospitalVO;
import com.nxg.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description  住院办理的控制器
 */
@Controller
@RequestMapping("hospital")
@ResponseBody
public class HospitalController {
    @Autowired
    private HospitalService hospitalService;

    /**
     * 分页查询住院信息
     * @param pageNum
     * @param pageSize
     * @param vo
     * @return
     */
    @RequestMapping(value = "list",method = RequestMethod.GET)
    public ResultVO<Hospital> queryByPage(Integer pageNum, Integer pageSize, QueryHospitalVO vo){
        if (pageNum==null||pageNum<=0){
            pageNum=1;
        }
        if (pageSize==null||pageSize<=0){
            pageSize=5;
        }
        PageInfo<Hospital> hospitalPageInfo = hospitalService.queryByPage(pageNum, pageSize, vo);
        return new ResultVO<>(hospitalPageInfo);
    }


}
