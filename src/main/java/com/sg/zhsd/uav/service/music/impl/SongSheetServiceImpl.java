package com.sg.zhsd.uav.service.music.impl;

import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sg.zhsd.uav.data.SongSheet;
import com.sg.zhsd.uav.data.dto.SongSheetDto;
import com.sg.zhsd.uav.mapper.SongSheetMapper;
import com.sg.zhsd.uav.service.music.SongSheetService;
import com.sg.zhsd.uav.utils.C;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jiangcong
 * @since 2020-08-19
 */
@Service
public class SongSheetServiceImpl extends ServiceImpl<SongSheetMapper, SongSheet> implements SongSheetService {


    /**
     * 创建歌单
     * @param songSheetDto
     * @return
     */
    @Override
    public SongSheetDto addSongSheet(SongSheetDto songSheetDto) {

        try {

            SongSheet insertSongSheet = C.copy(songSheetDto, SongSheet.class);
            insertSongSheet.setCreateDate(new Date());
            save(insertSongSheet);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return songSheetDto;
    }


    /**
     * 删除歌单
     * @param id
     * @return
     */
    @Override
    public Object deleteSongSheet(Long id) {

        try {

            deleteSongSheet(id);
        } catch (Exception e) {
            e.printStackTrace();
            return R.failed("删除失败!");
        }

        return R.ok("删除成功!");
    }
}
