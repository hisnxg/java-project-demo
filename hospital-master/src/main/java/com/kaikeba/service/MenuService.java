package com.kaikeba.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kaikeba.mapper.MenuMapper;
import com.kaikeba.pojo.Menu;
import com.kaikeba.pojo.MenuExample;
import com.kaikeba.vo.QueryUserRoleMenuVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 菜单逻辑层
 */
@Service
public class MenuService {

    @Resource
    private MenuMapper menuMapper;

    //1.添加菜单
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = {Exception.class})
    public int insertMenu(Menu menu){
        return menuMapper.insertSelective(menu);
    }

    //2.根据id更新菜单
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = {Exception.class})
    public int updateMenu(Menu menu){
        return menuMapper.updateByPrimaryKeySelective(menu);
    }

    //3.根据id删除菜单 --更新菜单状态值
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = {Exception.class})
    public int deleteMenu(Integer id){
        Menu menu = new Menu();
        menu.setId(id);
        menu.setStatus(1); // 1 无效
        return menuMapper.updateByPrimaryKeySelective(menu);
    }

    //4.分页+多条件查询
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = {Exception.class})
    public PageInfo<Menu> selectByPage(Integer pageNum, Integer pageSize, QueryUserRoleMenuVO vo){
        //拼接sql条件
        MenuExample menuExample = new MenuExample();
        MenuExample.Criteria criteria = menuExample.createCriteria();
        if (vo.getMenuName()!=null&&!"".equals(vo.getMenuName())){
            criteria.andMenuNameLike("%"+vo.getMenuName()+"%");
        }
        //分页 只对下一行的sql有效
        PageHelper.startPage(pageNum,pageSize);
        List<Menu> menus = menuMapper.selectByExample(menuExample);
        return new PageInfo<>(menus); //装填
    }

    //5.查询所有菜单 用于回显
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = {Exception.class})
    public List<Menu> selectAllMenu() {
        System.out.println("service");
        //要求有效的菜单才能回显
        MenuExample menuExample = new MenuExample();
        MenuExample.Criteria criteria = menuExample.createCriteria();
        criteria.andStatusEqualTo(0);
        return menuMapper.selectByExample(menuExample);
    }

    //6.删除所有选中菜单
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = {Exception.class})
    public boolean deleteSelected(String[] menuIds) {
        if (menuIds.length==0) {
            System.out.println("数据接收异常");
            return false;
        }
        Menu menu = new Menu();
        for (String menuid : menuIds) {
            menu.setId(Integer.parseInt(menuid));
            menu.setStatus(1); //1 无效
            int delete = menuMapper.updateByPrimaryKeySelective(menu);
            if (delete!=1) return false;
        }
        return true;
    }

    //7.根据id查询菜单 用于回显
    public Menu selectById(Integer menuId){
        System.out.println("待更新菜单的id："+menuId);
        return  menuMapper.selectByPrimaryKey(menuId);
    }


    //8.通过菜单名称查询菜单 --菜单名称校验
    public boolean selectByMenuName(String menuName) {
        MenuExample menuExample = new MenuExample();
        MenuExample.Criteria criteria = menuExample.createCriteria();
        criteria.andMenuNameEqualTo(menuName);
        List<Menu> menuList = menuMapper.selectByExample(menuExample);
        if (menuList.size()==0) return false;
        return true;
    }
}
