package com.kkb.service.doctormanager.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kkb.bean.Doctor;
import com.kkb.dao.DoctorMapper;
import com.kkb.service.doctormanager.DoctorManagerService;
import com.kkb.utils.FileUtils;
import com.kkb.vo.doctormanager.req.AddDoctorParam;
import com.kkb.vo.doctormanager.req.SelectDoctorReq;
import com.kkb.vo.doctormanager.req.SelectOneParam;
import com.kkb.vo.doctormanager.req.UpdateDoctorReq;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author mj
 * @create 2021-03-18 18:34
 * @describe:
 */
@Service
public class DoctorManagerServiceImpl implements DoctorManagerService {

    @Autowired
    private DoctorMapper doctorMapper;

    private static final String[] TITLES ={"医生编号","医生姓名","入院时间","所属科室"};

    /**
     * 查询医生
     * @param param 筛选条件
     * @return
     */
    @Override
    public PageInfo<Doctor> queryDoctorByParam(SelectDoctorReq param) {
        PageHelper.startPage(param.getPageNum(),param.getPageSize());
        List<Doctor> doctorList = doctorMapper.selectDoctorByParam(param);
        return new PageInfo<>(doctorList,3);
    }

    /**
     * 添加医生
     * @param param 参数
     */
    @Override
    public void addDoctor(AddDoctorParam param) {
        Doctor doctor = new Doctor();
        BeanUtils.copyProperties(param,doctor);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            if (!StringUtils.isEmpty(param.getDBirthday())) {
                doctor.setdBirthday(format.parse(param.getDBirthday()));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        doctor.setdIntime(new Date());
        doctor.setdState(0);
        doctorMapper.insert(doctor);
    }

    /**
     * 根据名字查询医生信息
     * @param param
     * @return
     */
    @Override
    public Map<String, Object> selectDoctorByName(SelectOneParam param) {
        Map<String,Object> map = new HashMap<>();
        Doctor doctor = doctorMapper.selectDoctorByName(param);
        map.put("doctor",doctor);
        return map;
    }

    /**
     * 修改医生信息
     * @param param
     */
    @Override
    public void updateDoctor(UpdateDoctorReq param) {
        Doctor doctor = new Doctor();
        BeanUtils.copyProperties(param,doctor);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            if (!StringUtils.isEmpty(param.getDBirthday())) {
                doctor.setdBirthday(format.parse(param.getDBirthday()));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        doctorMapper.updateByPrimaryKeySelective(doctor);
    }

    @Override
    public void exportExcelDoctor(List<Integer> doctorIds) {
        List<Doctor> doctorList = doctorMapper.selectDocterByIds(doctorIds);
        //TODO 调用生产excel文件的方法
        try {
            String doctorExcel = FileUtils.createDoctorExcel(TITLES, doctorList);
            System.out.println(doctorExcel);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除
     * @param doctorIds
     */
    @Override
    public void deleteDoctorById(Integer doctorIds) {
        doctorMapper.deleteByPrimaryKey(doctorIds);
    }

    /**
     * 导出住院结算的细腻些
     * @param doctorIds
     */
    @Override
    public void deleteDoctorByIds(Integer[] doctorIds) {
        List<Integer> integers = Arrays.asList(doctorIds);
        doctorMapper.deleteDoctorByIds(integers);
    }

    /**
     * 根据科室查询医生信息
     * @param keshi
     * @return
     */
    @Override
    public Map<String, Object> selectDoctorByKeshi(String keshi) {
        Map<String,Object> map = new HashMap<>();
        List<Doctor> doctors = doctorMapper.selectDoctorByKeshi(keshi);
        map.put("doctors",doctors);
        return map;
    }
}
