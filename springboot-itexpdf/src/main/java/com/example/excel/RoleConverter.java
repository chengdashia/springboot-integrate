package com.example.excel;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.metadata.property.ExcelContentProperty;

/**
 * @author 成大事
 * @since 2022/8/17 7:59
 */
public class RoleConverter implements Converter<Integer> {

    @Override
    public WriteCellData<?> convertToExcelData(Integer value, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        if(value == -1){
            return new WriteCellData<>("未认证");
        }else if(value == 0){
            return new WriteCellData<>("农户认证");
        }else if(value == 1){
            return new WriteCellData<>("公司认证");
        }
        return new WriteCellData<>(String.valueOf(value));
    }

    @Override
    public Class<?> supportJavaTypeKey() {
        return Integer.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }


}
