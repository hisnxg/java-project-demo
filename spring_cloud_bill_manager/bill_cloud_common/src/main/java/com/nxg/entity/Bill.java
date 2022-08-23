package com.nxg.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author nxg
 * date 2022/1/13
 * @apiNote 账单 实体类
 */
@Table(name = "bill_")
public class Bill implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_")
    private Long id;

    @Column(name = "title_")
    private String title;

    @Column(name = "bill_time_")
    private Date billTime; //账单时间

    @Column(name = "type_id_")
    private Long typeId; //账单类型

    @Column(name = "price_")
    private Double price;

    @Column(name = "explain_")
    private String explain; //描述

    @Transient
    private String typeName;//类型名称

    @Transient  //跟表中的字段没有关联
    private Date date1;//开始时间

    @Transient
    private Date date2; //结束时间

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getBillTime() {
        return billTime;
    }

    public void setBillTime(Date billTime) {
        this.billTime = billTime;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Date getDate1() {
        return date1;
    }

    public void setDate1(Date date1) {
        this.date1 = date1;
    }

    public Date getDate2() {
        return date2;
    }

    public void setDate2(Date date2) {
        this.date2 = date2;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", billTime=" + billTime +
                ", typeId=" + typeId +
                ", price=" + price +
                ", explain='" + explain + '\'' +
                ", typeName='" + typeName + '\'' +
                ", date1=" + date1 +
                ", date2=" + date2 +
                '}';
    }
}
