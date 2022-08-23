package com.nxg.dao;

import com.nxg.domain.User;

import java.util.List;

/**
 * @author nxg
 * date 2022/1/9
 * @apiNote
 */
public interface UserDao {

    public List<User> findAll();
}
