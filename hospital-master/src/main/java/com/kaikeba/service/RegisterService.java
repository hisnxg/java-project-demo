package com.kaikeba.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kaikeba.mapper.RegisterMapper;
import com.kaikeba.pojo.Register;
import com.kaikeba.pojo.RegisterExample;
import com.kaikeba.vo.QueryRegisterVO;
import com.kaikeba.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
/**
 *   挂号管理的控制器
 */
@Service
public class RegisterService {
    @Autowired
    private RegisterMapper registerMapper;

    /**
     * 多条件分页查询挂号信息
     * @param pageNum
     * @param pageSize
     * @param vo
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED,readOnly = true)
    public PageInfo<Register> queryByPage(Integer pageNum, Integer pageSize, QueryRegisterVO vo) {
        RegisterExample registerExample=new RegisterExample();
        RegisterExample.Criteria criteria = registerExample.createCriteria();
        if (vo!=null){

        }
        if (pageNum!=null&&pageSize!=null){
            PageHelper.startPage(pageNum,pageSize);
        }
        List<Register> registers = registerMapper.selectByExample(registerExample);
        return new PageInfo<>(registers);
    }

    /**
     * 根据主键查询挂号信息
     * @param id
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED,readOnly = true)
    public Register findById(Integer id){
        return registerMapper.selectByPrimaryKey(id);
    }


    /**
     * 根据挂号姓名模糊查询挂号信息
     * @param registerName
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED,readOnly = true)
    public List<Register> queryByName(String registerName){
        RegisterExample registerExample=new RegisterExample();
        RegisterExample.Criteria criteria = registerExample.createCriteria();
        if (registerName!=null&&!"".equals(registerName)){
            criteria.andRegisterNameLike("%"+registerName+"%");
        }
        List<Register> registers = registerMapper.selectByExample(registerExample);
        return registers;
    }
}
