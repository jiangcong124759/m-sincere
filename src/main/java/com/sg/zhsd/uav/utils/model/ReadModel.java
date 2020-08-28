package com.sg.zhsd.uav.utils.model;


import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ReadModel {

    @ExcelProperty(index = 0)
    private String fileName;

    @ExcelProperty(index = 1)
    private String fileId;

    @ExcelProperty(index = 2)
    private String fileMd5Value;

    @ExcelProperty(index = 3)
    private String uploadUserName;

    @ExcelProperty(index = 4)
    private String uploadDate;
}
