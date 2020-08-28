package com.sg.zhsd.uav.data;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author jiangcong
 * @since 2020-08-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SongSheet implements Serializable {

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

    /**
     * 创建时间
     */
    private Date createDate;


    public class Meta {
      public static final String ID = "id";

      public static final String SONG_SHEET_NAME = "song_sheet_name";

      public static final String TYPE = "type";

      public static final String USER_ID = "user_id";

      public static final String USER_NAME = "user_name";

      public static final String CREATE_DATE = "create_date";
    }
}
