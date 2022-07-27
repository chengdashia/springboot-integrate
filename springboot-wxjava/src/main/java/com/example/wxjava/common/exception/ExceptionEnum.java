package com.example.wxjava.common.exception;

import lombok.Getter;

/**
 * @author 成大事
 * @since 2022/6/13 21:36
 */
public enum ExceptionEnum{
    /**数据操作错误定义**/
    SUCCESS("2000", "成功!"),
    /**请求的数据格式不符**/
    BODY_NOT_MATCH("4000","请求的数据格式不符!"),
    /**请求的数字签名不匹配**/
    SIGNATURE_NOT_MATCH("4001","请求的数字签名不匹配!"),
    /**未找到该资源**/
    NOT_FOUND("4004", "未找到该资源!"),
    /**服务器内部错误**/
    INTERNAL_SERVER_ERROR("5000", "服务器内部错误!"),
    /**参数格式不合规**/
    CONSTRAINT_VIOLATION_EXCEPTION("5002", "参数格式不合规!"),
    /**请求参数不合规**/
    REQUEST_PARAMETER_EXCEPTION("5003", "请求参数不合规!"),
    /**登录过期。或token无效！**/
    NOT_LOGIN("5004", "登录过期。或token无效！"),
    /** 角色异常 **/
    NOT_ROLE("5005", "角色异常！"),
    /**权限不够**/
    NOT_PERMISSION("5006", "权限不够！"),
    /**权限不够**/
    DIS_LOGIN("5007", "账号被封禁了！"),
    /**服务器正忙，请稍后再试!**/
    SERVER_BUSY("5008","服务器正忙，请稍后再试!");



    /**自定义错误码*/
    @Getter
    private final String resultCode;

    /**
     * 错误描述
     */
    @Getter
    private final String resultMsg;


    ExceptionEnum(String resultCode, String resultMsg) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }

}
