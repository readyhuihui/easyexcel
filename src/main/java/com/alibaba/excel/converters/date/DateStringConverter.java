package com.alibaba.excel.converters.date;

import java.text.ParseException;
import java.util.Date;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.property.ExcelContentProperty;
import com.alibaba.excel.util.DateUtils;

/**
 * Date and string converter
 *
 * @author zhuangjiaju
 */
public class DateStringConverter implements Converter<Date> {
    @Override
    public Class supportJavaTypeKey() {
        return Date.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }

    @Override
    public Date convertToJavaData(CellData cellData, ExcelContentProperty contentProperty) throws ParseException {
        if (contentProperty.getDateTimeFormatProperty() == null) {
            return DateUtils.parseDate(cellData.getStringValue(), null);
        } else {
            return DateUtils.parseDate(cellData.getStringValue(),
                contentProperty.getDateTimeFormatProperty().getFormat());
        }
    }

    @Override
    public CellData convertToExcelData(Date value, ExcelContentProperty contentProperty) {
        if (contentProperty.getDateTimeFormatProperty() == null) {
            return new CellData(DateUtils.format(value, null));
        } else {
            return new CellData(DateUtils.format(value, contentProperty.getDateTimeFormatProperty().getFormat()));
        }
    }
}
