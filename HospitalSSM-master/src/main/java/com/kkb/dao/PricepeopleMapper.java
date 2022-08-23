package com.kkb.dao;

import com.kkb.bean.Pricepeople;

public interface PricepeopleMapper {
    int insert(Pricepeople record);

    int insertSelective(Pricepeople record);
}