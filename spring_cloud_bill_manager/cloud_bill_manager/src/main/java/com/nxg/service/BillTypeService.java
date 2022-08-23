package com.nxg.service;

import com.nxg.entity.BillType;
import com.nxg.mapper.BillTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author nxg
 * date 2022/1/13
 * @apiNote 账单类型 - 业务逻辑层
 */
@Service
public class BillTypeService {

    //@Autowired
    @Resource
    private BillTypeMapper billTypeMapper;

    public List<BillType> list(){
        return billTypeMapper.selectAll();
    }
}
