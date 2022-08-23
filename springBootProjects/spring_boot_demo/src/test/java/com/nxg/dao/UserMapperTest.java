package com.nxg.dao;

import com.nxg.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author nxg
 * date 2022/1/9
 * @apiNote
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testFindUser(){
        List<User> users = userMapper.selectAll();
        for(User u:users){
            System.out.println(u);
        }
    }

    @Test
    public void testFindUserPrimary(){
        User user = userMapper.selectByPrimaryKey(5);
        System.out.println(user);
    }

    @Test
    public void testInsertUser(){
        User user = new User();
        user.setUserName("xulongxiang");
        user.setName("徐龙象");
        user.setAge(28);
        user.setNote("在骑鹤下江南！");
        int insert = userMapper.insert(user);
        System.out.println(insert);
    }

    @Test
    public void testSelectByExample(){
        Example example = new Example(User.class);
       example.createCriteria().andLike("name", "%a%");
        List<User> users = userMapper.selectByExample(example);
        for (User u : users){
            System.out.println(u);
        }
    }

    /**
     * 自定义
     */
    @Test
    public void testfindByUser(){
        User user = new User();
        user.setName("b");
        user.setNote("g");
        List<User> byUser = userMapper.findByUser(user);
        for (User user1 : byUser) {
            System.out.println(byUser);
        }
    }
}