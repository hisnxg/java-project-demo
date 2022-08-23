package com.kkb.service.Drugpeople.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kkb.bean.Drug;
import com.kkb.bean.Drugpeople;
import com.kkb.dao.DrugMapper;
import com.kkb.dao.DrugpeopleMapper;
import com.kkb.service.Drugpeople.DrugpeopleService;
import com.kkb.vo.Drugpeople.req.SelectDrugpeopleReq;
import com.kkb.vo.drugmanager.req.AddDrugNumberParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author sufenglong
 * @date 2021/3/24 22:23
 * @describe:
 */
@Transactional
@Service
public class DrugpeopleServiceImpl implements DrugpeopleService {

    @Autowired
    private DrugMapper drugMapper;

    @Autowired
    private DrugpeopleMapper drugpeopleMapper;

    /**
     * 添加
     *
     * @param drugpeople
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addSendDrug(Drugpeople drugpeople) {

        drugpeople.setDrCreateTime(new Date());

        drugpeople.setDrNumberIssued(0);
        drugpeople.setDrNumberWeifa(drugpeople.getDrNumber());

        //查询是否有这种药品，如果有则数据的累加，如果没有则添加信息数据
        Map<String,Object> map = new HashMap<>();
        map.put("drugId",drugpeople.getDrId());
        map.put("peopleId",drugpeople.getPeopleId());
        //判断是否重复
        Drugpeople drugpeopleCF = drugpeopleMapper.selectChongFu(map);

        //如果以存在则进行药品数量的增加
        if (drugpeopleCF!=null){
            drugpeopleCF.setDrNumber(drugpeopleCF.getDrNumber() + drugpeople.getDrNumber());
            drugpeopleCF.setDrNumberWeifa(drugpeopleCF.getDrNumberWeifa() + drugpeople.getDrNumber());
            drugpeopleMapper.updateSendDrug(drugpeopleCF);
        }else {
            //添加发药
            drugpeopleMapper.addSendDrug(drugpeople);
        }

        //TODO 把这个查询优化掉
        Drug drug = drugMapper.selectByPrimaryKey(drugpeople.getDrId());

        AddDrugNumberParam addDrugNumberParam = new AddDrugNumberParam();
        addDrugNumberParam.setDrugId(drug.getDrId());
        addDrugNumberParam.setAddNumber(drug.getDrNumber() - drugpeople.getDrNumber());
        drugMapper.updateNumberByPrimaryKey(addDrugNumberParam);
    }

    /**
     * 条件查询
     *
     * @param param
     * @return
     */
    @Override
    public PageInfo<Drugpeople> selectDrugPeopleByParam(SelectDrugpeopleReq param) {
        PageHelper.startPage(param.getPageNum(), param.getPageSize());
        List<Drugpeople> drugpeople = drugpeopleMapper.selectDrugPeopleByParam(param);
        return new PageInfo<>(drugpeople);
    }


    /**
     * 详情显示
     *
     *
     * @param pageNum
     * @param peopleId
     * @return
     */
    @Override
    public Map<String,Object> selectDrugPeopleById(Integer peopleId,Integer pageNum) {
        Map<String,Object> map = new HashMap<>();
        PageHelper.startPage(pageNum,5);
        List<Drugpeople> drugpeopleList = drugpeopleMapper.selectDrugPeopleById(peopleId);
        PageInfo<Drugpeople> drugpeoplePageInfo = new PageInfo<>(drugpeopleList);
        map.put("items",drugpeoplePageInfo);
        return map;
    }


    @Override
    public void updateSendDrug30(Drugpeople drugpeople) {
        Drugpeople DR = drugpeopleMapper.getByDreId(drugpeople.getPeopleId());
        if ((DR.getDrNumber() - DR.getDrNumberIssued()) < 30) {
            drugpeople.setDrNumberIssued(DR.getDrNumber());
            drugpeopleMapper.updateSendDrug(drugpeople);
        } else {
            drugpeople.setDrNumberIssued(DR.getDrNumberIssued() + 30);
            drugpeopleMapper.updateSendDrug(drugpeople);
        }
    }

    @Override
    public void updateSendDrug1(Drugpeople drugpeople) {
        Drugpeople DR = drugpeopleMapper.getByDreId(drugpeople.getPeopleId());
        if ((DR.getDrNumber() - DR.getDrNumberIssued()) < 1) {
            drugpeople.setDrNumberIssued(DR.getDrNumber());
            drugpeopleMapper.updateSendDrug(drugpeople);
        } else {
            drugpeople.setDrNumberIssued(DR.getDrNumberIssued() + 1);
            drugpeopleMapper.updateSendDrug(drugpeople);
        }
    }

    @Override
    public void updateSendDrug5(Drugpeople drugpeople) {
        Drugpeople DR = drugpeopleMapper.getByDreId(drugpeople.getPeopleId());
        if ((DR.getDrNumber() - DR.getDrNumberIssued()) < 5) {
            drugpeople.setDrNumberIssued(DR.getDrNumber());
            drugpeopleMapper.updateSendDrug(drugpeople);
        } else {
            drugpeople.setDrNumberIssued(DR.getDrNumberIssued() + 5);
            drugpeopleMapper.updateSendDrug(drugpeople);
        }

    }

    @Override
    public void updateSendDrugAll(Drugpeople drugpeople) {
        Drugpeople DR = drugpeopleMapper.getByDreId(drugpeople.getPeopleId());
        drugpeople.setDrNumberIssued(DR.getDrNumber());
        drugpeopleMapper.updateSendDrug(drugpeople);
    }


    /**
     * 全能发药
     *
     * @param drugpeople
     */
    @Override
    public void updateSendDrug(Drugpeople drugpeople) {

        Drugpeople Dr = drugpeopleMapper.getByDreId(drugpeople.getDpId());

        Dr.setDrNumberIssued(Dr.getDrNumber());
        Dr.setDrNumberWeifa(Dr.getDrNumber() - Dr.getDrNumberIssued());

        drugpeopleMapper.updateSendDrug(drugpeople);

    }

    @Override
    public Drugpeople selectByPrimaryKey(Integer peopleId) {
        return drugpeopleMapper.selectByPrimaryKey(peopleId);
    }

    @Override
    public Drugpeople selectByHosrIdAndDrugId(Integer peopleId, Integer drugId) {
        Map<String,Object> map = new HashMap<>();
        map.put("drugId",drugId);
        map.put("peopleId",peopleId);
        return drugpeopleMapper.selectChongFu(map);
    }


}
