package com.sg.zhsd.uav.service.music;


import com.baomidou.mybatisplus.extension.service.IService;
import com.sg.zhsd.uav.data.SongSheet;
import com.sg.zhsd.uav.data.dto.SongSheetDto;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jiangcong
 * @since 2020-08-19
 */
public interface SongSheetService extends IService<SongSheet> {

    /**
     * 创建歌单
     * @param songSheetDto
     * @return
     */
    SongSheetDto addSongSheet(SongSheetDto songSheetDto);


    /**
     * 删除歌单
     * @param id
     * @return
     */
    Object deleteSongSheet(Long id);
}
