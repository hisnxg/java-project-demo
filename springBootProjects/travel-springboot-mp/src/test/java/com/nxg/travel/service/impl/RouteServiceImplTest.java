package com.nxg.travel.service.impl;

import com.github.pagehelper.PageInfo;
import com.nxg.travel.domain.Route;
import com.nxg.travel.service.RouteService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author nxg
 * date 2022/1/17
 * @apiNote
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RouteServiceImplTest {

    @Autowired
    private RouteService routeService;

    @Test
    public void findPage() {
        Route condition = new Route();
        condition.setRname("北京");

        PageInfo<Route> page = routeService.findPage(condition,1,10);

        page.getList().forEach(r -> {
            System.out.println(r.getRid() + "\t" + r.getRname() + "\t" +
                    r.getCategory().getCname() + "\t" + r.getSeller().getSname() + "\t" +
                    r.getRouteImgList().size());
        });
    }

    @Test
    public void findById() {
        Route r = routeService.findById(34);
        System.out.println(r.getRid() + "\t" + r.getRname() + "\t" + r.getCategory().getCname() +
                "\t" + r.getSeller().getSname() + "\t" + r.getRouteImgList().size());
    }
}