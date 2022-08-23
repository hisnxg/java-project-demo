package com.kkb.service.drugmanager.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kkb.bean.Doctor;
import com.kkb.bean.Drug;
import com.kkb.dao.DrugMapper;
import com.kkb.service.drugmanager.DrugManagerService;
import com.kkb.utils.FileUtils;
import com.kkb.vo.drugmanager.req.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  @author: MaXia
 *  @Date: 2021/3/19 0:12
 *  @Description:
 */
@Service
public class DrugManagerServiceImpl implements DrugManagerService {

    @Autowired
    private DrugMapper drugMapper;

    private static final String[] TITLES ={"药品编号","药品名称","药品类型"};

    /**
     * 查询药品
     * @param param 筛选条件
     * @return
     */
    @Override
    public PageInfo<Drug> queryDrugByParam(SelectDrugReq param) {
        //1.指定分页数据
        PageHelper.startPage(param.getPageNum(),param.getPageSize());
        //2.查询数据
        List<Drug> drugList = drugMapper.selectDrugByParam(param);
        //3.创建分页工具类
        PageInfo<Drug> drugPageInfo = new PageInfo<>(drugList);
        return drugPageInfo;
    }

    @Override
    public int addNewDrug(AddDrugParam param) {
        Drug drug = new Drug();
        BeanUtils.copyProperties(param,drug);
        int flag = drugMapper.insert(drug);
        return flag;
    }

    /**
     * 根据药品编号查询
     * @param param
     * @return
     */
    @Override
    public Map<String, Object> selectDrugByDrugId(SelectOneParam param) {
        Map<String,Object > map = new HashMap<>();
        Drug drug = drugMapper.selectByPrimaryKey(param.getDrugId());
        map.put("drug",drug);
        return map;
    }

    /**
     * 添加库存
     * @param param
     * @return
     */
    @Override
    public void addDrugNumber(AddDrugNumberParam param) {
        Drug drug = drugMapper.selectByPrimaryKey(param.getDrugId());
        Integer drNumber = drug.getDrNumber();
        Integer addNumber = param.getAddNumber();
        System.out.println(addNumber);
        param.setAddNumber(Integer.valueOf(drNumber.intValue()+addNumber.intValue()));
        drugMapper.updateNumberByPrimaryKey(param);
    }

    /**
     * 更改药品信息
     * @param param
     */
    @Override
    public void updateByPrimaryKeySelective(UpdateDrugParam param) {
        Drug drug = new Drug();
        BeanUtils.copyProperties(param,drug);
        drugMapper.updateByPrimaryKeySelective(drug);
    }

    @Override
    public void exportExcelDrug(List<Integer> drugIds) {
        List<Drug> drugList = drugMapper.selectDrugByIds(drugIds);
        //TODO 调用生产excel文件的方法
        try {
            String drugExcel = FileUtils.createDrugExcel(TITLES, drugList);
            System.out.println(drugExcel);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
