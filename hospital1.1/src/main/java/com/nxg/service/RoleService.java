package com.nxg.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nxg.mapper.RoleMapper;
import com.nxg.mapper.RoleMenuMapper;
import com.nxg.pojo.Menu;
import com.nxg.pojo.Role;
import com.nxg.pojo.RoleExample;
import com.nxg.pojo.RoleMenuExample;
import com.nxg.vo.QueryUserRoleMenuVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RoleService {

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private RoleMenuService roleMenuService;

    @Resource
    private MenuService menuService;

    @Resource
    private RoleMenuMapper roleMenuMapper;

    //1.分页多条件查询角色
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = {Exception.class})
    public PageInfo<Role> selectByPage(Integer pageNum, Integer pageSize, QueryUserRoleMenuVO vo) {
        RoleExample roleExample = new RoleExample();//盛放条件的对象
        RoleExample.Criteria criteria = roleExample.createCriteria();//准则
        criteria.andRoleNameLike("%"+vo.getRoleName()+"%");

        PageHelper.startPage(pageNum,pageSize); //分页
        List<Role> roles = roleMapper.selectByExample(roleExample);
        return new PageInfo<>(roles); //填充
    }


    //2.添加角色  表单传递角色基本信息+被选中的菜单id
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = {Exception.class})
    public boolean insertRole(Role role,String[] menuids){
        //添加角色并返回角色id  对应的sql加上useGeneratedKeys="true" keyProperty="id"
        int insert = roleMapper.insertSelective(role);
        System.out.println("新添加的角色id是:"+role.getId());
        return roleMenuService.insertRoleMenu(role.getId(), menuids);//中间表绑定roleId和menuids
    }

    //3.更新角色  表单传递角色基本信息+被选中的菜单id
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = {Exception.class})
    public boolean updateRole(Role role,String[] menuids){
        int update = roleMapper.updateByPrimaryKeySelective(role);//更新角色表
        if (update==1){   //更新中间表
            //先根据角色id删除中间表，再添加更新后的菜单
            RoleMenuExample roleMenuExample = new RoleMenuExample();
            RoleMenuExample.Criteria criteria = roleMenuExample.createCriteria();
            criteria.andRoleIdEqualTo(role.getId());
            int delete = roleMenuMapper.deleteByExample(roleMenuExample);
            return roleMenuService.insertRoleMenu(role.getId(), menuids);
        }
        return false;
    }

    //4.根据id查询角色
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = {Exception.class})
    public Role selectById(Integer roleId) {
        Role role = roleMapper.selectByPrimaryKey(roleId);
        List<Menu> menuList = roleMenuService.selectMenuByRoleId(roleId);
        if (menuList!=null){
           role.setMenuList(menuList);
        }
        return role;
    }

    //5.批量删除
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = {Exception.class})
    public boolean deleteSelected(String[] roleIds){
        if (roleIds!=null){
            Role role = new Role();
            for (String roleId : roleIds) {
                role.setId(Integer.parseInt(roleId));
                role.setStatus(1); // 1 禁用
                int update = roleMapper.updateByPrimaryKeySelective(role);
                if (update==0) return false;
            }
            return true;
        }
        return false;
    }

    //6.通过角色名称查询角色 --角色唯一性验证
    public boolean selectByRoleName(RoleExample roleExample) {
        List<Role> roles = roleMapper.selectByExample(roleExample);
        if (roles.size()>0) return true;
        return false;
    }
}
