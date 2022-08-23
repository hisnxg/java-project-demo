package com.nxg.controller;

import com.github.pagehelper.PageInfo;
import com.nxg.pojo.Register;
import com.nxg.service.RegisterService;
import com.nxg.vo.QueryRegisterVO;
import com.nxg.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *挂号管理的控制器
 */
@Controller
@RequestMapping("register")
@ResponseBody
public class RegisterController {
    @Autowired
    private RegisterService registerService;

    /**
     * 多条件分页查询挂号信息
     * @param pageNum
     * @param pageSize
     * @param vo
     * @return
     */
    @RequestMapping(value = "list",method = RequestMethod.GET)
    public ResultVO<Register> findByPage(Integer pageNum, Integer pageSize, QueryRegisterVO vo){
        if (pageNum==null||pageNum<=0){
            pageNum=1;
        }
        if (pageSize==null||pageSize<=0){
            pageSize=5;
        }
        PageInfo<Register> registerPageInfo = registerService.queryByPage(pageNum, pageSize, vo);
        return new ResultVO<>(registerPageInfo);
    }
}
