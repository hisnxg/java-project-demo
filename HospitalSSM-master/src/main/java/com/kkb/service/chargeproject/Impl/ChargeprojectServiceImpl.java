package com.kkb.service.chargeproject.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kkb.bean.Chargeproject;
import com.kkb.dao.ChargeprojectMapper;
import com.kkb.service.chargeproject.ChargeprojectService;
import com.kkb.vo.chargeproject.req.AddChargeprojectParam;
import com.kkb.vo.chargeproject.req.ChaChongParam;
import com.kkb.vo.chargeproject.req.SelectChergeproject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ChargeprojectServiceImpl implements ChargeprojectService {


    @Autowired
    private ChargeprojectMapper chargeprojectMapper;


    /**
     * 添加收费项目
     *
     * @param param
     */
    @Override
    public void addChargeproject(AddChargeprojectParam param) {


        Chargeproject chargeproject = new Chargeproject();


        chargeproject.setChapStime( new Date());

        BeanUtils.copyProperties(param, chargeproject);
        chargeprojectMapper.insertSelective(chargeproject);
    }

    /**
     * 查询 分页
     *
     * @param param
     * @return
     */
    @Override
    public PageInfo<Chargeproject> selectChargeproject(SelectChergeproject param) {

        PageHelper.startPage(param.getPageNum(), param.getPageSize());
        List<Chargeproject> list = chargeprojectMapper.selectByPrimaryKey(param);
        return new PageInfo<>(list);

    }


    /**
     * 添加查重
     *
     * @param param
     * @return
     */
    @Override
    public Chargeproject selectByChapName(ChaChongParam param) {
        Chargeproject chargeproject = chargeprojectMapper.selectByChapName(param);
        return chargeproject;
    }

    /**
     * 修改数据回显
     *
     * @param chapId
     */
    @Override
    public Chargeproject selectByChapId(Chargeproject chapId) {
        return chargeprojectMapper.selectByChapId(chapId);
    }

    /**
     * 修改
     *
     * @param chapId
     */
    @Override
    public void updateByPrimaryKeySelective(Chargeproject chapId) {
        chargeprojectMapper.updateByPrimaryKeySelective(chapId);
    }

    /**
     * 删除
     *
     * @param chapId
     */
    @Override
    public void deleteById(Integer chapId) {
        chargeprojectMapper.deleteByPrimaryKey(chapId);
    }


}
