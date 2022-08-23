package com.kaikeba;

import com.github.pagehelper.PageInfo;
import com.kaikeba.mapper.RoleMapper;
import com.kaikeba.pojo.Menu;
import com.kaikeba.pojo.Role;
import com.kaikeba.pojo.SysUser;
import com.kaikeba.service.MenuService;
import com.kaikeba.service.SysUserService;
import com.kaikeba.vo.QueryUserRoleMenuVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户 角色 菜单 测试类
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring.xml"})
public class SysUserTest {

    @Resource
    private SysUserService sysUserService;

    @Resource
    private MenuService menuService;

    @Resource
    private RoleMapper roleMapper;

    //添加用户
    @Test
    public void insertSysUser(){
        SysUser sysUser = new SysUser("haha","haha","哈哈","121@qq.com",0,1);
        int insert = sysUserService.insertUser(sysUser);
        System.out.println(insert);
    }

    //根据id删除用户
    @Test
    public void deleteSysUser(){
        int delete = sysUserService.deleteSysUser(2);
        System.out.println(delete);
    }

    //分页+条件查询测试
    @Test
    public void selectByPage(){
        QueryUserRoleMenuVO vo = new QueryUserRoleMenuVO();
        vo.setLoginName("hehe");
        PageInfo<SysUser> pageInfo = sysUserService.selectByPage(1, 5, vo);
        for (SysUser sysUser : pageInfo.getList()) {
            System.out.println(sysUser);
        }
    }

    //根据id更新用户
    @Test
    public void updateSysUser(){
        SysUser sysUser = new SysUser();
        sysUser.setLoginName("hehe");
        sysUser.setTrueName("呵呵");
        sysUser.setStatus(0);
        sysUser.setId(2);
        int update = sysUserService.updateSysUser(sysUser);
        System.out.println(update);
    }


    //添加菜单
    @Test
    public void insertMenu(){
        Menu menu = new Menu("保安管理", "/baoan.html", 0);
        int insert = menuService.insertMenu(menu);
        System.out.println(insert);
    }

    //根据id更新菜单
    @Test
    public void updateMenu(){
        Menu menu = new Menu(13, "保安信息", "/security.html", 1);
        int update = menuService.updateMenu(menu);
        System.out.println(update);
    }

    //根据id删除菜单
    @Test
    public void deleteMenu(){
        int delete = menuService.deleteMenu(13);
        System.out.println(delete);
    }

    //分页多条件查询菜单
    @Test
    public void selectMenuByPage(){
        QueryUserRoleMenuVO vo = new QueryUserRoleMenuVO();
        vo.setMenuName("管理");
        PageInfo<Menu> menuPageInfo = menuService.selectByPage(1, 5, vo);
        for (Menu menu : menuPageInfo.getList()) {
            System.out.println(menu);
        }
    }

    //获取所有角色
    @Test
    public void selectAllRole(){
        List<Role> roles = sysUserService.selectAllRole();
        for (Role role : roles) {
            System.out.println(role);
        }
    }

}