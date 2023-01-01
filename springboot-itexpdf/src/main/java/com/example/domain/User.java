package com.example.domain;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentStyle;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import com.alibaba.excel.enums.poi.HorizontalAlignmentEnum;
import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.example.excel.RoleConverter;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author 成大事
 * @since 2022-08-14 19:28:00
 */
@Data
@HeadRowHeight(20)
@ColumnWidth(25)
@ContentStyle(horizontalAlignment = HorizontalAlignmentEnum.CENTER)
@Accessors(chain = true)
@TableName("user")
public class User extends Model<User> {

    private static final long serialVersionUID = 1L;

    //@Excel(name = "主键id",width = 20)
    @ExcelProperty(value= "主键id")
    @TableId(value = "user_id", type = IdType.ASSIGN_ID)
    private Long id;

    //@Excel(name = "微信用户的openid",width = 20)
    @TableField("user_openid")
    @ExcelProperty("微信用户的openid")
    private String openid;



    @TableField("user_screen_name")
    //@Excel(name = "用户昵称")
    @ExcelProperty("用户昵称")
    private String screenName;

    //@Excel(name = "用户状态",replace = {"未认证_-1","农户认证_0","公司认证_1"})
    @ExcelProperty(value = "用户状态",converter = RoleConverter.class)
    @TableField("user_status")
    private Integer status;


    //@Excel(name = "注册时间",exportFormat = "yyyy-MM-dd HH:mm:ss",width = 30)
    @ExcelProperty("注册时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;


    //@Excel(name = "最后登录时间",exportFormat = "yyyy-MM-dd HH:mm:ss",width = 30)
    @ExcelProperty("最后登录时间")
    @TableField("login_time")
    private Date loginTime;


    public static final String USER_ID = "user_id";

    public static final String USER_OPENID = "user_openid";

    public static final String USER_SCREEN_NAME = "user_screen_name";

    public static final String USER_STATUS = "user_status";

    public static final String CREATE_TIME = "create_time";

    public static final String LOGIN_TIME = "login_time";

    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
