package com.dong.shiro.model.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 成大事
 * @since 2022/12/31 13:00
 */
@Data
@TableName("user")
@AllArgsConstructor
@NoArgsConstructor
public class User {


    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    @TableField("name")
    private String name;

    @TableField("pwd")
    private String pwd;

    @TableField("rid")
    private Integer rid;

    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String PWD = "pwd";
    public static final String RID = "rid";
}
