package com.nxg.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nxg.mapper.BehospitalMapper;
import com.nxg.mapper.DeptMapper;
import com.nxg.mapper.DoctorMapper;
import com.nxg.mapper.HosRegisterMapper;
import com.nxg.pojo.*;
import com.nxg.vo.QueryHosRegisterVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class HosRegisterService {
    //挂号
    @Autowired
    private HosRegisterMapper mapper;
    //住院
    @Autowired
    private BehospitalMapper behospitalMapper;

    //医生表
    @Autowired
    private DoctorMapper doctorMapper;

    @Autowired
    private DeptMapper deptMapper;

    /**
     * 添加挂号表
     * @param hosRegister
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = {Exception.class})
    public int add(HosRegister hosRegister){
        int insert = mapper.insertSelective(hosRegister);
        System.out.println("挂号添加数据===>"+insert);
        int i = behospitalMapper.insertSelective(hosRegister.getBehospital());
        System.out.println("住院添加数据===>"+i);
//        Doctor doctor = new Doctor();
//        doctor.setId(Integer.valueOf(d_id));
//        doctor.setDeptId(Integer.valueOf(deptId));
//        doctorMapper.updateByPrimaryKeySelective(doctor);
        return insert;
    }

    /**
     * 查询所有挂号 挂号表中包含住院信息
     * @return
     */
    public List<HosRegister> queryAll(Integer pageNum, Integer pageSize, QueryHosRegisterVO queryHosRegisterVO) {
        HosRegisterExample hosRegisterExample = new HosRegisterExample();
        List<HosRegister> hosRegisters = mapper.selectByExample(hosRegisterExample);
        for (HosRegister hosRegister : hosRegisters) {
            BehospitalExample behospitalExample = new BehospitalExample();
            BehospitalExample.Criteria criteria = behospitalExample.createCriteria();
            criteria.andBeH_idEqualTo(hosRegister.getHosR_id());
            List<Behospital> behospitals = behospitalMapper.selectByExample(behospitalExample);
            for (Behospital behospital : behospitals) {
                hosRegister.setBehospital(behospital);
            }
            //每个挂号表对应一个医生信息
            Doctor doctor = doctorMapper.selectByPrimaryKey(hosRegister.getD_id());
            Dept dept = deptMapper.selectByPrimaryKey(doctor.getDeptId());
            hosRegister.setDept(dept);
            hosRegister.setDoctor(doctor);
        }
        return hosRegisters;
    }

    public PageInfo<HosRegister> queryAll1(Integer pageNum, Integer pageSize,QueryHosRegisterVO queryHosRegisterVO) {
        HosRegisterExample hosRegisterExample = new HosRegisterExample();
        HosRegisterExample.Criteria criteria1 = hosRegisterExample.createCriteria();
        if(queryHosRegisterVO != null){
            if(!"".equals(queryHosRegisterVO.getHosR_id())){
                criteria1.andHosR_idEqualTo(queryHosRegisterVO.getHosR_id());
            }
            if(queryHosRegisterVO.getHosR_name() != null && !"".equals(queryHosRegisterVO.getHosR_name().trim())){
                criteria1.andHosR_nameLike(queryHosRegisterVO.getHosR_name());
            }
        }
        PageHelper.startPage(pageNum,pageSize);
        List<HosRegister> hosRegisters = mapper.selectByExample(hosRegisterExample);
//        for (HosRegister hosRegister : hosRegisters) {
//            BehospitalExample behospitalExample = new BehospitalExample();
//            BehospitalExample.Criteria criteria = behospitalExample.createCriteria();
//            criteria.andBeH_idEqualTo(hosRegister.getHosR_id());
//            List<Behospital> behospitals = behospitalMapper.selectByExample(behospitalExample);
//            for (Behospital behospital : behospitals) {
//                hosRegister.setBehospital(behospital);
//            }
//        }
        return new PageInfo<>(hosRegisters);
    }

    public HosRegister queryById(String hosR_id) {
        HosRegisterExample hosRegisterExample = new HosRegisterExample();
        HosRegisterExample.Criteria criteria = hosRegisterExample.createCriteria();
        criteria.andHosR_idEqualTo(Integer.valueOf(hosR_id));
        List<HosRegister> hosRegisters = mapper.selectByExample(hosRegisterExample);
        Doctor doctor = doctorMapper.selectByPrimaryKey(hosRegisters.get(0).getD_id());
        hosRegisters.get(0).setDoctor(doctor);
        Dept dept = deptMapper.selectByPrimaryKey(doctor.getDeptId());
        BehospitalExample behospitalExample = new BehospitalExample();
        BehospitalExample.Criteria criteria1 = behospitalExample.createCriteria();
        criteria1.andBeH_idEqualTo(Integer.valueOf(hosR_id));
        List<Behospital> behospitals = behospitalMapper.selectByExample(behospitalExample);
        hosRegisters.get(0).setDept(dept);
        hosRegisters.get(0).setBehospital(behospitals.get(0));
        return hosRegisters.get(0);
    }
//    //多条件分页查询
//    public PageInfo<Team> queryByPage(Integer pageNum, Integer pageSize, TeamVo teamVo) throws ParseException {
//        TeamExample teamExample = new TeamExample();
//        //多条件查询容器
//        TeamExample.Criteria criteria = teamExample.createCriteria();
//        if(teamVo != null){
//            if(teamVo.getTeamName() != null && !"".equals(teamVo.getTeamName().trim())){
//                criteria.andTeamNameLike("%"+teamVo.getTeamName()+"%");
//            }
//            if(teamVo.getChineseName() != null && !"".equals(teamVo.getChineseName().trim())){
//                criteria.andChineseNameLike("%"+teamVo.getChineseName()+"%");
//            }
//            if(teamVo.getCoach() != null){
//                criteria.andCoachLike("%"+teamVo.getCoach()+"%");
//            }
//            if(teamVo.getBeginDate() != null){
//                criteria.andCreateTimeGreaterThanOrEqualTo(teamVo.getBeginDate());
//            }
//            if(teamVo.getEndDate() != null){
//                criteria.andCreateTimeLessThanOrEqualTo(teamVo.getEndDate());
//            }
//            if(teamVo.getArea() != null && teamVo.getArea() != -1){
//                criteria.andAreaEqualTo(teamVo.getArea());
//            }
//        }
//        PageHelper.startPage(pageNum,pageSize);
//        List<Team> teams = teamMapper.selectByExample(teamExample);
//
//        return new PageInfo<>(teams);
//    }
}
