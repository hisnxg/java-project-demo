package com.nxg.dao;

import com.nxg.domain.User;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author nxg
 * date 2022/1/9
 * @apiNote
 */
public interface UserMapper extends Mapper<User> {

    public List<User> findByUser(User user);
}
