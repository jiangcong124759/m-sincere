package com.sg.zhsd.uav.data;

import lombok.Data;

@Data
public class SourceFile {

    private String fileName;

    private String fileId;

    private String fileMd5Value;

    private String uploadUserName;

    private String uploadDate;
}
