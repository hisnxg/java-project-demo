package com.kaikeba.dao.imp;

import com.alibaba.druid.sql.visitor.functions.Now;
import com.kaikeba.bean.Express;
import com.kaikeba.dao.BaseExpressDao;
import com.kaikeba.exception.DuplicateCodeException;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class ExpressDaoMysqlTest {
    BaseExpressDao dao = new ExpressDaoMysql();
    @Test
    public void console() {
        List<Map<String, Integer>> console = dao.console();
        System.out.println(console);

    }

    @Test
    public void findAll() {
//        List<Express> all = dao.findAll(true, 2, 2);
        List<Express> all = dao.findAll(true, 0, 0);
        System.out.println(all);
    }

    @Test
    public void findByNumber() {
        Express byNumber = dao.findByNumber("12365");
        System.out.println(byNumber);
    }

    @Test
    public void findBycode() {
        Express bycode = dao.findBycode("122366");
        System.out.println(bycode);
    }

    @Test
    public void findByUserPhone() {
        List<Express> byUserPhone = dao.findByUserPhone("16607259999");
        System.out.println(byUserPhone);
    }

    @Test
    public void findBySysPhone() {
        List<Express> bySysPhone = dao.findBySysPhone("16607256666");
        System.out.println(bySysPhone);
    }

    @Test
    public void insert() {

        //INSERT INTO EXPRESS (NUMBER,USERNAME,USERPHONE,COMPANY,CODE,INTIME,STATUS,SYSPHONE) VALUES(?,?,?,?,?,NOW(),0,?)
        Express e = new Express("1212125","赵六","15571259966","顺丰快递","585856","18888886888");
        boolean insert = false;
        try {
            insert = dao.insert(e);
        } catch (DuplicateCodeException duplicateCodeException) {
//            duplicateCodeException.printStackTrace();
            System.out.println("取件码重复的异常被捕获到了");
        }
        System.out.println(insert);

    }
    @Test
    public void insert2() {

        //INSERT INTO EXPRESS (NUMBER,USERNAME,USERPHONE,COMPANY,CODE,INTIME,STATUS,SYSPHONE) VALUES(?,?,?,?,?,NOW(),0,?)

        boolean insert = false;
        try {
            for (int i=0;i<100;i++){
                Express e = new Express("12121"+i,"赵六","15571259966","顺丰快递","5858"+i,"18888886888");
                insert = dao.insert(e);
            }

        } catch (DuplicateCodeException duplicateCodeException) {
            System.out.println("取件码重复的异常被捕获到了");
        }
        System.out.println(insert);

    }
    @Test
    public void update() {

        Express e = new Express();
        e.setNumber("222224");
        e.setCompany("嘿嘿快递");
        e.setUsername("王五");
        e.setStatus(1);
        boolean update = dao.update(1, e);
        System.out.println(update);
    }

    @Test
    public void updateStatus() {

        boolean FLAG= dao.updateStatus("585856");
        System.out.println(FLAG);
    }

    @Test
    public void delete() {
        boolean d = dao.delete(1);
        System.out.println(d);
    }
}