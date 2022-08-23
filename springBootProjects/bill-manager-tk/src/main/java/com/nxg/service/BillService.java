package com.nxg.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nxg.entity.Bill;
import com.nxg.mapper.BillMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author nxg
 * date 2022/1/13
 * @apiNote 账单-业务逻辑层
 */
@Service
public class BillService {

    @Autowired
    private BillMapper billMapper;

    public List<Bill> billList(Bill b){
       return billMapper.select(b);
    }

    public  Bill get(Long id){
        return billMapper.selectByPrimaryKey(id);
    }
    public int add(Bill b){
       return billMapper.insert(b);
    }

    public int delete(Long id){
        return billMapper.deleteByPrimaryKey(id);
    }

    public int update(Bill bill){
      return billMapper.updateByPrimaryKey(bill);
    }

    public PageInfo<Bill> listPage(Bill b,int pageNum,int pageSize){
        return PageHelper.startPage(pageNum,pageSize).doSelectPageInfo(() -> {
            billMapper.select(b);
        });
    }

}
