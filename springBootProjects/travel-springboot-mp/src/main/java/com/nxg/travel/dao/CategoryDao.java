package com.nxg.travel.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nxg.travel.domain.Category;
import org.apache.ibatis.annotations.Select;

/**
 * @author nxg
 * date 2022/1/17
 * @apiNote
 */
public interface CategoryDao extends BaseMapper<Category> {

    @Select("select cname,cid from tab_category where cid=#{cid}")
    public Category findById(Integer id);
}
