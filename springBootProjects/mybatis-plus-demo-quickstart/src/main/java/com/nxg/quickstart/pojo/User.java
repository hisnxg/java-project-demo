package com.nxg.quickstart.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author nxg
 * date 2022/1/10
 * @apiNote
 */
@Data
@Accessors(chain = true) //链式存储值
@TableName("user3")
public class User {

    @TableId
    private Long id;
    private String name;
    private Integer age;
    private String email;

    @TableField(exist = false)
    private Integer count;


}
