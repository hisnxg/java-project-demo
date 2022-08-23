package com.nxg.travel.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nxg.travel.domain.Seller;
import org.apache.ibatis.annotations.Select;

/**
 * @author nxg
 * date 2022/1/17
 * @apiNote 公司dao 数据访问层
 */
public interface SellerDao extends BaseMapper<Seller> {

    @Select("SELECT * FROM tab_seller WHERE sid=#{id}")
    public Seller findById(Integer id);
}
