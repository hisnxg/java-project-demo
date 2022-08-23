package com.nxg.quickstart.mapper;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nxg.quickstart.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.nio.file.Watchable;
import java.util.Arrays;
import java.util.List;


/**
 * @author nxg
 * date 2022/1/10
 * @apiNote
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelect2(){
//        List<User> list = userMapper.selectList(null);
//        for (User user : list) {
//            System.out.println(user);
//        }
        userMapper.selectList(null).forEach(System.out :: println);
    }

//    @Test
//    public void testInsert(){
//        User user = new User();
//        user.setName();
//        userMapper.insert(user);
//    }

    @Test
    public void testDeleteById(){
//        int i = userMapper.deleteById(10l);
//        System.out.println(i);
//        userMapper.selectList(null).forEach(System.out :: println);
        //批量删除 1
        //userMapper.delete(new QueryWrapper<User>().like("name","J"));

        //批量删除 2
        //userMapper.delete(Wrappers.<User>query().like("name","J"));

        //批量删除 3
        //userMapper.delete(Wrappers.<User>query().lambda().like(User :: getName,"J"));

        userMapper.selectList(null).forEach(System.out :: println);

    }

    @Test
    public void testUpdate(){
        //基本修改
        //userMapper.updateById(new User().setId(1l).setName("张三"));
        //批量修改 1
        //userMapper.update(null,Wrappers.<User>update().set("email","123@163.com").like("name","J"));
        //批量修改 2
        //userMapper.update(new User().setEmail("2222.@163.com"), Wrappers.<User>update().like("name","J"));

        userMapper.selectList(null).forEach(System.out :: println);
    }

    @Test
    public void testSelect(){
        //基本查询
        //System.out.println(userMapper.selectOne(Wrappers.<User>query().eq("name","Tom")));

        //投影查询
        userMapper.selectList(Wrappers.<User>query().select("id","name")).forEach(System.out :: println);

    }


    @Test
    public void testPage(){
        System.out.println("baseMapper -------自带分页");
        Page<User> page = new Page<>(2,5);
        IPage<User> pageResult = userMapper.selectPage(page, Wrappers.<User>query());
        System.out.println("总条数="+pageResult.getTotal());
        System.out.println("总页数="+pageResult.getPages());
        System.out.println("当前页数="+pageResult.getCurrent());
        System.out.println("当前页显示的条数="+pageResult.getSize());
        pageResult.getRecords().forEach(System.out :: println);
    }

    @Test
    public void testXmlPage(){
        System.out.println("baseMapper 自定义Xml 分页");
        Page<User> page = new Page<>(2,10);
        User u = new User();
        u.setAge(331);
        u.setEmail("test4");
        IPage<User> pageResult = userMapper.selectUserByPage2(page, u);
        System.out.println("总条数="+pageResult.getTotal());
        System.out.println("总页数="+pageResult.getPages());
        System.out.println("当前页数="+pageResult.getCurrent());
        System.out.println("当前页的显示条数="+pageResult.getSize());
        pageResult.getRecords().forEach(System.out :: println);

    }

    @Test
    public void testPageHelper() {
        // pagehelper
        // PageInfo<User> page = PageHelper.startPage(1, 2).doSelectPageInfo(() ->userMapper.selectList(Wrappers.<User>query()));
        PageHelper.startPage(1,2);
        // PageInfo<User> page = new PageInfo<>(mapper.selectList(Wrappers.<User>query()));
        User u = new User();
        u.setAge(20);
        PageInfo<User> page = new PageInfo<User>(userMapper.selectUserByPage3(u));
        List<User> list = page.getList();
        System.out.println("总行数=" + page.getTotal());
        System.out.println("当前页=" + page.getPageNum());
        System.out.println("每页行数=" + page.getPageSize());
        System.out.println("总页数=" + page.getPages());
        System.out.println("起始行数=" + page.getStartRow());
        System.out.println("是第一页=" + page.isIsFirstPage());
        System.out.println("是最后页=" + page.isIsLastPage());
        System.out.println("还有下一页=" + page.isHasNextPage());
        System.out.println("还有上一页=" + page.isHasPreviousPage());
        System.out.println("页码列表" + Arrays.toString(page.getNavigatepageNums()));
    }

}