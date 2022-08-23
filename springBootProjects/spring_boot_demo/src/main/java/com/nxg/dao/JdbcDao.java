package com.nxg.dao;

import com.nxg.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author nxg
 * date 2022/1/8
 * @apiNote
 */
@Repository
public class JdbcDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<User> findUserAll(){
        return jdbcTemplate.query("select * from tb_user",new BeanPropertyRowMapper<>(User.class));
    }
}
