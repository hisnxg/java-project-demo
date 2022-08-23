
package com.kaikeba.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kaikeba.mapper.DeptMapper;
import com.kaikeba.mapper.DoctorMapper;
import com.kaikeba.pojo.Dept;
import com.kaikeba.pojo.DeptExample;
import com.kaikeba.pojo.Doctor;
import com.kaikeba.pojo.DoctorExample;
import com.kaikeba.vo.QueryDoctorVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 医生
 */
@Service
public class DoctorService {
	@Resource
	private DoctorMapper doctorMapper;
	@Resource
	private DeptMapper deptMapper;

	/**
	 * 分页查询
	 *
	 * @param pageNum  当前页
	 * @param pageSize 每页显示的数据条数
	 * @param vo       查询条件
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,readOnly = true)
	public PageInfo<Doctor> queryByPage(Integer pageNum, Integer pageSize, QueryDoctorVO vo) {
		//多条件
		DoctorExample example = new DoctorExample();
		DeptExample deptExample = new DeptExample();

		//创建条件的容器
		DoctorExample.Criteria criteria = example.createCriteria();

		DeptExample.Criteria dCriteria = deptExample.createCriteria();
		if (vo.getDeptName() != null && !"".equals(vo.getDeptName().trim())) {
			dCriteria.andDeptNameLike("%" + vo.getDeptName().trim() + "%");
		}

		List<Dept> deptList =deptMapper.selectByExample(deptExample);

		if (vo != null) {
			if (vo.getDoctorId() != null && !"".equals(vo.getDoctorId())) {
				criteria.andDoctorIdEqualTo(vo.getDoctorId());
			}
			if (vo.getDoctorName() != null && !"".equals(vo.getDoctorName().trim())) {
				criteria.andDoctorNameLike("%" + vo.getDoctorName().trim() + "%");
			}
			if(deptList!=null){
				criteria.andDeptIdEqualTo(deptList.get(0).getDeptId());
			}
		}
		//分页
		PageHelper.startPage(pageNum,pageSize);
		List<Doctor> doctorList=doctorMapper.selectByExample(example);
		return new PageInfo<>(doctorList);
	}

	/**
	 * 添加医生
	 * @param doctor
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor = {Exception.class})
	public int addDoctor(Doctor doctor){
		return doctorMapper.insert(doctor);
	}

	/**
	 * 主键查询
	 * @param doctorId
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,readOnly = true)
	public Doctor queryById(int doctorId){
		return doctorMapper.selectByPrimaryKey(doctorId);
	}

	/**
	 * 根据主键更新医生信息
	 * @param id
	 * @param doctor
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,readOnly = true)
	public int updateDoctor(int id,Doctor doctor){
		return doctorMapper.updateByPrimaryKeySelective(doctor);
	}

	/**
	 * 添加挂号时查询所有医生
	 * @return
	 */
    public List<Doctor> queryAllDoctor() {
		DoctorExample doctorExample = new DoctorExample();
		return doctorMapper.selectByExample(doctorExample);
	}
}

