package com.kaikeba;

import com.kaikeba.pojo.DrugHospital;
import com.kaikeba.service.DispensingService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring.xml"})
public class DispensingTest {
    @Resource
    DispensingService service;

    @Test
    public void test(){

    }
}
