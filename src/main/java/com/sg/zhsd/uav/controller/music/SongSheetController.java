package com.sg.zhsd.uav.controller.music;


import com.baomidou.mybatisplus.extension.api.R;
import com.sg.zhsd.uav.data.SongSheet;
import com.sg.zhsd.uav.data.dto.SongSheetDto;
import com.sg.zhsd.uav.service.music.SongSheetService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jiangcong
 * @since 2020-08-19
 */
@Api(tags = {"歌单管理"})
@RestController
@RequestMapping("/song-sheet")
@CrossOrigin(allowCredentials = "true") //允许跨域请求
@EnableAutoConfiguration
public class SongSheetController {

    @Autowired
    SongSheetService songSheetService;

    @PostMapping("/song-sheet")
    @ApiOperation(value = "创建歌单")
    public R addSongSheet(@RequestBody SongSheetDto songSheetDto)  {

        return R.ok(songSheetService.addSongSheet(songSheetDto));
    }


    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除歌单")
    public R deleteSongSheet(@PathVariable Long id)  {

        return R.ok(songSheetService.deleteSongSheet(id));
    }


}
