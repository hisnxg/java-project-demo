package com.kaikeba.service;

import com.kaikeba.mapper.MenuMapper;
import com.kaikeba.mapper.RoleMenuMapper;
import com.kaikeba.pojo.Menu;
import com.kaikeba.pojo.Role;
import com.kaikeba.pojo.RoleMenu;
import com.kaikeba.pojo.RoleMenuExample;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 角色-菜单 中间表 进行关联 给角色绑定菜单
 */
@Service
public class RoleMenuService {

    @Resource
    private RoleMenuMapper roleMenuMapper;
    @Resource
    private MenuMapper menuMapper;

    //1.给角色绑定菜单
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = {Exception.class})
    public boolean insertRoleMenu(Integer roleId, String[] menuids){
        if (menuids!=null&&menuids.length>0){
            for (int i = 0; i < menuids.length; i++) {
                int insert = roleMenuMapper.insertSelective(new RoleMenu(roleId, Integer.parseInt(menuids[i])));
                if (insert!=1) return false;
            }
        }
        return true;
    }

    //2.通过roleId查询所有menu
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = {Exception.class})
    public List<Menu>selectMenuByRoleId(Integer roleId){
        RoleMenuExample roleMenuExample = new RoleMenuExample();
        RoleMenuExample.Criteria criteria = roleMenuExample.createCriteria();
        if (roleId!=null){
            criteria.andRoleIdEqualTo(roleId);
            List<RoleMenu> menuList = roleMenuMapper.selectByExample(roleMenuExample);
            List<Menu>list=new ArrayList<>();
            for (RoleMenu roleMenu : menuList) {
                list.add(menuMapper.selectByPrimaryKey(roleMenu.getMenuId()));
            }
            return list;
        }
        return null;
    }

}
