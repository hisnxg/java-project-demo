package com.kaikeba.service;

import com.kaikeba.mapper.SysUserMapper;
import com.kaikeba.pojo.SysUser;
import com.kaikeba.pojo.SysUserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SystemService {
    @Autowired
    private SysUserMapper sysUserMapper;

    /**
     * 系统登录
     * @param userName
     * @param password
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED,readOnly = true)
    public SysUser login(String userName,String password){
        SysUserExample sysUserExample=new SysUserExample();
        SysUserExample.Criteria criteria = sysUserExample.createCriteria();
        if (userName!=null&&!"".equals(userName)){
            criteria.andLoginNameEqualTo(userName);
        }
        if (password!=null&&!"".equals(password)){
            criteria.andPasswordEqualTo(password);
        }
        List<SysUser> sysUsers = sysUserMapper.selectByExample(sysUserExample);
        if (sysUsers!=null&&sysUsers.size()>0){
            return sysUsers.get(0);
        }
        return null;
    }
}
