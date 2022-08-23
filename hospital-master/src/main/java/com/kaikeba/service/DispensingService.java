package com.kaikeba.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kaikeba.mapper.*;
import com.kaikeba.pojo.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Service
public class DispensingService {
    @Resource
    HospitalMapper hospitalMapper;

    @Resource
    RegisterMapper registerMapper;

    @Resource
    DoctorMapper doctorMapper;

    @Resource
    DrugHospitalMapper  drugHospitalMapper;

    @Resource
    DrugMapper drugMapper;

    /**
     * 多条件查询在线发药列表信息
     * @param pageNum
     * @param pageSize
     * @param hid
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED,readOnly = true)
    public PageInfo<Hospital> queryByPage(Integer pageNum, Integer pageSize, Integer hid){
        if(hid!=null && hid!=0){
            PageHelper.startPage(pageNum,pageSize);
            //住院信息
            Hospital hospital = hospitalMapper.selectByPrimaryKey(hid);
            //设置住院的病人
            hospital.setRegister(registerMapper.selectByPrimaryKey(hospital.getRegisterId()));
            Register register = hospital.getRegister();
            //设置病人的负责医生
            register.setDoctor(doctorMapper.selectByPrimaryKey(register.getDoctorId()));
            //完善住院信息
            hospital.setRegister(register);
            ArrayList<Hospital> list = new ArrayList<>();
            list.add(hospital);
            return new PageInfo<>(list);
        }else{
            PageHelper.startPage(pageNum,pageSize);
            List<Hospital> hospitals = hospitalMapper.selectByExample(null);
            for (Hospital hospital : hospitals) {
                hospital.setRegister(registerMapper.selectByPrimaryKey(hospital.getRegisterId()));
                Register register = hospital.getRegister();
                //设置病人的负责医生
                register.setDoctor(doctorMapper.selectByPrimaryKey(register.getDoctorId()));
                //完善住院信息
                hospital.setRegister(register);
            }
            for (Hospital hospital : hospitals) {
                System.out.println(hospital);

            }

            return new PageInfo<>(hospitals);
        }

    }

    /**
     * 根据病例号查询病人的用药信息
     * @param hid
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED,readOnly = true)
    public List<Drug> queryById(Integer hid){
        DrugHospitalExample drugHospitalExample = new DrugHospitalExample();
        DrugHospitalExample.Criteria criteria = drugHospitalExample.createCriteria();
        criteria.andHospitalIdEqualTo(hid);
        //病人和药品对应表
        List<DrugHospital> drugHospitalList = drugHospitalMapper.selectByExample(drugHospitalExample);
        ArrayList<Drug> list = new ArrayList<>();
        for (DrugHospital drugHospital : drugHospitalList) {
            Drug drug = drugMapper.selectByPrimaryKey(drugHospital.getDrugId());
            list.add(drug);
        }
        return list;
    }

    /**
     * 发药
     * @param hid
     * @param drugId
     * @param number
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = {Exception.class})
    public Boolean sendDrug(Integer hid,Integer drugId,Integer number){
        DrugHospitalExample example = new DrugHospitalExample();
        DrugHospitalExample.Criteria criteria = example.createCriteria();
        criteria.andDrugIdEqualTo(drugId);
        criteria.andHospitalIdEqualTo(hid);
        List<DrugHospital> drugHospitals = drugHospitalMapper.selectByExample(example);
        Drug drug = drugMapper.selectByPrimaryKey(drugId);
        //虽然这里获取的是一个集合，但是在实际业务中，该集合中对象的个数为1
        for (DrugHospital drugHospital : drugHospitals) {
            if(number > drugHospital.getNeed() && number > drug.getResetNumber()){
                return false;
            }else{
                //更新病人该药品所需要的数量
                drugHospital.setNeed(drugHospital.getNeed()-number);
                drugHospital.setOwn(drugHospital.getOwn()+number);
                int i = drugHospitalMapper.updateByPrimaryKey(drugHospital);
                //更新总药品所需要的数量
                drug.setResetNumber(drug.getResetNumber()-number);
                drug.setSentNumber(drug.getSentNumber()+number);
                int j = drugMapper.updateByPrimaryKeySelective(drug);
                if(i==1 && j==1){
                    return true;
                }else{
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 查询所有的药品数量
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED,readOnly = true)
    public List<Drug> queryAllDrugs(){
        return drugMapper.selectByExample(null);
    }

    /**
     * 给选中的病人发药
     * @param drugId 病人的id
     * @param hidList 病人列表
     * @param number 发药数量
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = {Exception.class})
    public Boolean giveDrugs(Integer drugId,List<Integer> hidList,Integer number){
        Boolean flag = false;
        Drug oldDrug = drugMapper.selectByPrimaryKey(drugId);
        int size = hidList.size();
        if(size * number > oldDrug.getResetNumber()){
            //药品库存不足
            return false;
        }else{
            //更新总药品的数量
            Drug drug = drugMapper.selectByPrimaryKey(drugId);
            drug.setResetNumber(drug.getResetNumber()-number*size);
            drug.setSentNumber(drug.getSentNumber()+number*size);
            drugMapper.updateByPrimaryKeySelective(drug);
            //一次给每一个病人发药
            for (Integer hid : hidList) {
                DrugHospitalExample ex = new DrugHospitalExample();
                DrugHospitalExample.Criteria criteria = ex.createCriteria();
                criteria.andDrugIdEqualTo(drugId);
                criteria.andHospitalIdEqualTo(hid);
                //下面的集合中只有一个对象
                List<DrugHospital> drugHospitals = drugHospitalMapper.selectByExample(ex);
                if(drugHospitals.size()==0){
                    //如果药品-病人表中没有这条信息，插入该药品-病人对应信息
                    DrugHospital drugHospital = new DrugHospital();
                    drugHospital.setDrugId(drugId);
                    drugHospital.setHospitalId(hid);
                    drugHospital.setNeed(0);
                    drugHospital.setOwn(number);
                    int i = drugHospitalMapper.insertSelective(drugHospital);
                    if(i==1){
                        flag = true;
                    }else{
                        flag = false;
                    }
                }else{
                    for (DrugHospital d : drugHospitals) {
                        //更新药品病人表中的信息
                        d.setNeed(d.getNeed()-number);
                        d.setOwn(d.getOwn()+number);
                        int i = drugHospitalMapper.updateByPrimaryKey(d);
                        if(i==1){
                            flag = true;
                        }else{
                            flag = false;
                        }
                    }
                }
            }
        }
        return flag;
    }

    /**
     * 病人药品信息详情
     * @param hid
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED,readOnly = true)
    public PageInfo<DrugHospital> lookDrugs(Integer hid,Integer pageNum, Integer pageSize){
        DrugHospitalExample ex = new DrugHospitalExample();
        DrugHospitalExample.Criteria criteria = ex.createCriteria();
        criteria.andHospitalIdEqualTo(hid);
        //分页
        PageHelper.startPage(pageNum,pageSize);
        List<DrugHospital> drugHospitalList = drugHospitalMapper.selectByExample(ex);
        for (DrugHospital drugHospital : drugHospitalList) {
            Drug drug = drugMapper.selectByPrimaryKey(drugHospital.getDrugId());
            drugHospital.setDrug(drug);
        }
        return new PageInfo<>(drugHospitalList);
    }

    /**
     * 在发药详情页面中发药
     * @param hid
     * @param drugId
     * @param type
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = {Exception.class})
    public boolean grantDrugs(Integer hid, Integer drugId, Integer type){
        DrugHospitalExample ex = new DrugHospitalExample();
        DrugHospitalExample.Criteria criteria = ex.createCriteria();
        criteria.andHospitalIdEqualTo(hid);
        criteria.andDrugIdEqualTo(drugId);
        Drug drug = drugMapper.selectByPrimaryKey(drugId);
        List<DrugHospital> drugHospitals = drugHospitalMapper.selectByExample(ex);
        if(type == 1){
            //全发
            for (DrugHospital d : drugHospitals) {
               if(d.getNeed()>drug.getResetNumber()){
                   return false;
               }else{
                   Integer num = d.getNeed();
                   d.setOwn(num+d.getOwn());
                   d.setNeed(0);
                   drugHospitalMapper.updateByPrimaryKeySelective(d);
                   drug.setResetNumber(drug.getResetNumber()-num);
                   drug.setSentNumber(drug.getSentNumber()+num);
                   drugMapper.updateByPrimaryKeySelective(drug);
                   return true;
               }
            }
        }else if(type == 2){
            //发一
            for (DrugHospital d : drugHospitals) {
                if(1>drug.getResetNumber()|| 1>d.getNeed()){
                    return false;
                }else{
                    d.setOwn(1+d.getOwn());
                    d.setNeed(d.getNeed()-1);
                    drugHospitalMapper.updateByPrimaryKeySelective(d);
                    drug.setResetNumber(drug.getResetNumber()-1);
                    drug.setSentNumber(drug.getSentNumber()+1);
                    drugMapper.updateByPrimaryKeySelective(drug);
                    return true;
                }
            }

        }else if(type == 3){
            //发五
            for (DrugHospital d : drugHospitals) {
                if(5>drug.getResetNumber() || 5>d.getNeed()){
                    return false;
                }else{
                    d.setOwn(d.getOwn()+5);
                    d.setNeed(d.getNeed()-5);
                    drugHospitalMapper.updateByPrimaryKeySelective(d);
                    drug.setResetNumber(drug.getResetNumber()-5);
                    drug.setSentNumber(drug.getSentNumber()+5);
                    drugMapper.updateByPrimaryKeySelective(drug);
                    return true;
                }
            }
        }
        return false;
    }
}
