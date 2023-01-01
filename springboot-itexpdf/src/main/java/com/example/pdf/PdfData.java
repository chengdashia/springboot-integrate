package com.example.pdf;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ToString
public class PdfData {


    /** 甲方 */
    private String partA;

    /** 乙方 */
    private String partB;

    /** 类型 */
    private String type;

    /** 品种 */
    private String varieties;

    /** 数字 */
    private String nums;

    /** 价格 */
    private String price;

    /** 总价 */
    private String totalPrice;

    /** 全部 */
    private String total;

    /** 甲方地址 */
    private String partAAddress;

    /** 乙方地址 */
    private String partBAddress;

    /** 甲方的联系电话 */
    private String partAPhone;


    /** 乙方的联系电话 */
    private String partBPhone;

    /** 年 */
    private String year;

    /** 月 */
    private String month;

    /** 天 */
    private String day;



}