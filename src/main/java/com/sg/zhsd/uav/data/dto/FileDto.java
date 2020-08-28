package com.sg.zhsd.uav.data.dto;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("file")
public class FileDto {


    private String fileName;

    private String fileId;

    private String fileMd5Value;

    private String uploadUserName;

    private String uploadDate;

}
