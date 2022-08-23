package com.nxg.controller;

import com.github.pagehelper.PageInfo;
import com.nxg.pojo.Doctor;
import com.nxg.service.DoctorService;
import com.nxg.vo.QueryDoctorVO;
import com.nxg.vo.ResultVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * 医生的控制器
 */
@Controller
@RequestMapping("doctor")
@ResponseBody
public class DoctorController {
	@Resource
	private DoctorService doctorService;

	//多条件分页查询
	@RequestMapping(value = "list",method = RequestMethod.GET)
	public ResultVO<Doctor> queryByPage(Integer pageNum, Integer pageSize, QueryDoctorVO vo){
		System.out.println("分页查询===================");
		if(pageNum==null || pageNum<=0){
			pageNum=1;
		}
		if(pageSize==null || pageSize<=0){
			pageSize=5;
		}
		PageInfo<Doctor> teamPageInfo = doctorService.queryByPage(pageNum, pageSize, vo);
		return new ResultVO<>(teamPageInfo);
	}

	//添加医生
	@RequestMapping(value = "",method = RequestMethod.POST)
	public ResultVO<Doctor> addDoctor(Doctor doctor){
		int i = doctorService.addDoctor(doctor);
		if (i==1){
			return new ResultVO<Doctor>();
		}
		return new ResultVO<Doctor>(500,"服务器内部异常，请稍后重试！");
	}

	//添加挂号时查询医生列表 --mk
	@GetMapping("")
	public ResultVO<Doctor> queryAllDoctor(){
		List<Doctor> doctors = doctorService.queryAllDoctor();
		ResultVO<Doctor> doctorResultVO = new ResultVO<>();
		if(doctors != null){
			doctorResultVO.setCode(200);
			doctorResultVO.setList(doctors);
		}else{
			doctorResultVO.setCode(500);
			doctorResultVO.setMsg("今天没有医生小哥哥");
		}
		return doctorResultVO;
	}
}
