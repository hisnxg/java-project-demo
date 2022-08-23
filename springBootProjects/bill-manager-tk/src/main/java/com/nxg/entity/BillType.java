package com.nxg.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.lang.reflect.Type;

/**
 * @author nxg
 * date 2022/1/13
 * @apiNote 账单类型
 */
@Table(name = "bill_type_")
public class BillType implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_")
    private Long id;

    @Column(name = "name_")
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "BillType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
