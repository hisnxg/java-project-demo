package com.kaikeba.service;

import com.kaikeba.bean.Express;
import org.junit.Test;

import static org.junit.Assert.*;

public class ExpressServiceTest {

    @Test
    public void insert() {
        Express e = new Express("123666","赵六","16607259654","顺丰快递","585856","18888886888");
        boolean insert = ExpressService.insert(e);
        System.out.println(insert);
    }
}