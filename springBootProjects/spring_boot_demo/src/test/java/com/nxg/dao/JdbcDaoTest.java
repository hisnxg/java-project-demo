package com.nxg.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author nxg
 * date 2022/1/8
 * @apiNote
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class JdbcDaoTest {

    @Autowired
    private JdbcDao jdbcDao;

    @Test
    public void testFindAll(){
        jdbcDao.findUserAll().forEach(user -> {
            System.out.println(user);
        });
    }

}