package com.nxg.service;

import com.nxg.mapper.DeptMapper;
import com.nxg.pojo.Dept;
import com.nxg.pojo.DeptExample;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 部门
 */
@Service
public class DeptService {
	@Resource
	DeptMapper deptMapper;

	@Transactional(propagation = Propagation.REQUIRED,readOnly = true)
	public Dept selectByPrimaryKey(int deptId) {
		return deptMapper.selectByPrimaryKey(deptId);
	}
	@Transactional(propagation = Propagation.REQUIRED,readOnly = true)
	public List<Dept> selectByExample(){
		DeptExample example=new DeptExample();
		List<Dept> deptList = deptMapper.selectByExample(example);
		return deptList;
	}

}
