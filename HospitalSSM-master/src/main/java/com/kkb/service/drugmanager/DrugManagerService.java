package com.kkb.service.drugmanager;


import com.github.pagehelper.PageInfo;
import com.kkb.bean.Drug;
import com.kkb.vo.drugmanager.req.*;

import java.util.List;
import java.util.Map;

/**
 *  @author: MaXia
 *  @Date: 2021/3/18 23:55
 *  @Description:
 */
public interface DrugManagerService {
    //参数查询药品
    PageInfo<Drug> queryDrugByParam(SelectDrugReq param);

    int addNewDrug(AddDrugParam param);

    Map<String, Object> selectDrugByDrugId(SelectOneParam param);

    void addDrugNumber(AddDrugNumberParam param);

    void updateByPrimaryKeySelective(UpdateDrugParam param);

    void exportExcelDrug(List<Integer> drugIds);
}
