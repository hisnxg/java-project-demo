package com.kaikeba.dao.imp;

import com.kaikeba.bean.Courier;
import com.kaikeba.bean.Express;
import com.kaikeba.dao.BaseCourierDao;
import com.kaikeba.exception.DuplicateIdentifyCardException;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class CourierDaoMysqlTest {
BaseCourierDao dao =  new CourierDaoMysql();
    @Test
    public void consoleCourier() {
        List<Map<String, Integer>> maps = dao.consoleCourier();
        System.out.println(maps);
    }

    @Test
    public void findAllCourier() {
        List<Courier> bySysPhone = dao.findAllCourier(true,0,4);
        System.out.println(bySysPhone);
    }

    @Test
    public void findByCourierPhone() {
        List<Courier> byCourierPhone = dao.findByCourierPhone("16607259654");
        System.out.println(byCourierPhone);
    }

    @Test
    public void insertCourier() {
        Courier c = new Courier("张三","16607259663","421126198801125485","abc23");
        boolean flag = false;
        try {
            flag = dao.insertCourier(c);
        } catch (DuplicateIdentifyCardException duplicateIdentifyCardException) {
            duplicateIdentifyCardException.printStackTrace();
        }
        System.out.println(flag);
    }

    @Test
    public void updateCourier() {
        Courier courier = new Courier();
        courier.setUsername("李四");
        courier.setPassword("123456");
        boolean b = dao.updateCourier(2, courier);
        System.out.println(b);
    }

    @Test
    public void deleteCourier() {
        boolean b = dao.deleteCourier(3);
        System.out.println(b);
    }
}