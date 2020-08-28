package com.sg.zhsd.uav.data.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class SongSheetDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private Long id;

    /**
     * 歌单名称
     */
    private String songSheetName;

    /**
     * 歌单类型(1.喜欢，2.收藏 , 3.下载)
     */
    private Boolean type;

    /**
     * 所属人id
     */
    private String userId;

    /**
     * 所属人姓名
     */
    private String userName;
}
