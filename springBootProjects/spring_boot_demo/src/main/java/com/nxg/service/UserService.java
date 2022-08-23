package com.nxg.service;

import com.nxg.dao.UserMapper;
import com.nxg.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author nxg
 * date 2022/1/9
 * @apiNote
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public List<User> selectAll(){
        List<User> users = userMapper.selectAll();
        return users;
    }
}
