package com.nxg.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nxg.mapper.DrugMapper;
import com.nxg.pojo.Drug;
import com.nxg.pojo.DrugExample;
import com.nxg.vo.QueryDrugVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 *  药品管理
 */
@Service
public class DrugService {
    @Resource
    DrugMapper drugMapper;

    /**
     * 多条件分页查询
     * @param pageNum
     * @param pageSize
     * @param vo
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED,readOnly = true)
    public PageInfo<Drug> queryByPage(Integer pageNum, Integer pageSize, QueryDrugVO vo){
        //多条件
        DrugExample drugExample = new DrugExample();
        //存放多条件的容器
        DrugExample.Criteria criteria = drugExample.createCriteria();
        if(vo != null){
            if(vo.getDrugName()!=null && !"".equals(vo.getDrugName().trim())){
                criteria.andDrugNameLike("%"+vo.getDrugName()+"%");
            } if(vo.getDrugType()!=null && !"".equals(vo.getDrugType().trim())){
                criteria.andDrugTypeLike("%"+vo.getDrugType()+"%");
            }
        }
        //分页插件
        PageHelper.startPage(pageNum,pageSize);
        List<Drug> drugs = drugMapper.selectByExample(drugExample);
        return new PageInfo<>(drugs);
    }

    /**
     * 根据药品id查询药品信息
     * @param id 药品id
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED,readOnly = true)
    public Drug queryById(Integer id){
        return drugMapper.selectByPrimaryKey(id);
    }

    /**
     * 插入新的药品
     * @param drug
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = {Exception.class})
    public int addDrug(Drug drug){
        return drugMapper.insertSelective(drug);
    }

    /**
     * 选择性的更新药品
     * @param drug
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = {Exception.class})
    public int updateDrug(Drug drug){
        return drugMapper.updateByPrimaryKeySelective(drug);
    }

    /**
     * 查询所有内容不分页
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED,readOnly = true)
    public List<Drug> selectAll() {
        DrugExample drugExample = new DrugExample();
        List<Drug> drugs = drugMapper.selectByExample(drugExample);
        return drugs;
    }
}
