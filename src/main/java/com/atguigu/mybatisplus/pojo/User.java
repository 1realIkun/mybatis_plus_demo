package com.atguigu.mybatisplus.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

@Data
//@TableName("t_user")
public class User {

    //将属性所对应的字段是指定为主键
    //@TableId注解的value属性用于指定主键的字段
    @TableId(value = "uid",type = IdType.AUTO)
    private Long id;

    //指定属性所对应的字段名
    @TableField("user_name")
    private String name;

    private Integer age;

    private String email;

    //逻辑删除字段
    @TableLogic
    private Integer isDeleted;
}
