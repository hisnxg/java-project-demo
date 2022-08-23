package com.kkb.vo.drugmanager.req;

import com.kkb.bean.Drug;
import lombok.Data;

@Data
public class AddDrugNumberParam {


    private Integer drugId;
    private Integer addNumber;

    /**
     * sfl添加
     */
    private Drug drug;



}
