package com.nxg.travel.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.nxg.travel.dao.RouteImgDao;
import com.nxg.travel.domain.RouteImg;
import com.nxg.travel.service.RouteImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author nxg
 * date 2022/1/18
 * @apiNote
 */
@Service
public class RouteImgServiceImpl implements RouteImgService {

    @Autowired
    private RouteImgDao routeImgDao;

    /**
     * 处理线路图片
     *
     * @param rid       线路id
     * @param routeImgs 要添加的线路图片列表
     */
    @Override
    @Transactional
    public void saveImg(Integer rid, List<RouteImg> routeImgs) {
        routeImgDao.delete(Wrappers.<RouteImg>query().eq("rid",rid));
        for (RouteImg routeImg : routeImgs) {
            routeImgDao.insert(routeImg);
        }
    }
}
