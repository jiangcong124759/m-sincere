package com.sg.zhsd.uav.data.dto;

import lombok.Data;

import java.util.Date;

/**
 * SensitiveWordManageDto
 *
 * @author huangzd
 * @since 2019/5/5
 */
@Data
public class SensitiveWordManageDto {

    private Long id;//主键

    private Long kmSensitiveWordTypeId;//敏感词类型主键

    private Date createDatetime;//创建时间

    private Date updateDatetime;//更新时间

    private Long createOperator;//创建人

    private Long latestOperator;//最近修改人

    private String enumDeleted;//逻辑删除(deleted，normal)

    private String word;//语词

    private String description;//描述

    private Integer degree;//敏感程度


    /**
     * 校验字段
     */
    private String content;//校验内容

}
