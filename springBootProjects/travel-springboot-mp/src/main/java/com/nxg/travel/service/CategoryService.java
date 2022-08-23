package com.nxg.travel.service;

import com.nxg.travel.domain.Category;

import java.util.List;

/**
 * @author nxg
 * date 2022/1/17
 * @apiNote
 */
public interface CategoryService {

    public List<Category> find();

    /**
     * 根据id查询
     * @param id
     * @return
     */
    public Category findById(Integer id);

    /**
     * 添加
     * @param category
     * @return
     */
    public int add(Category category);

    /**
     * 修改
     * @param category
     * @return
     */
    public int update(Category category);

    /**
     * 删除
     * @param id
     * @return
     */
    public int delete(Integer id);
}
