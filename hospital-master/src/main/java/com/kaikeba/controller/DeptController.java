package com.kaikeba.controller;

import com.kaikeba.pojo.Dept;
import com.kaikeba.service.DeptService;
import com.kaikeba.vo.ResultVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * 部门
 */
@Controller
@RequestMapping("dept")
@ResponseBody
public class DeptController {

	@Resource
	DeptService deptService;
	//查询多有科室
	@RequestMapping(value = "list",method = RequestMethod.GET)
	public ResultVO<Dept> selectByExample() {
		List<Dept> deptList = deptService.selectByExample();
		ResultVO<Dept> vo=new ResultVO<>();
		if (deptList!=null){
			vo.setList(deptList);
		}else {
			vo.setCode(500);
			vo.setMsg("暂未设置科室");
		}
		return vo;
	}

	//主键查询
	@RequestMapping(value = "{id}",method = RequestMethod.GET)
	public ResultVO<Dept> findById(@PathVariable("id") int deptId){
		Dept dept = deptService.selectByPrimaryKey(deptId);
		return new ResultVO<>(dept);
	}

}
