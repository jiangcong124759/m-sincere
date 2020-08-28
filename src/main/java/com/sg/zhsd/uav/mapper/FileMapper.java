package com.sg.zhsd.uav.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sg.zhsd.uav.data.dto.FileDto;
import com.sg.zhsd.uav.data.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FileMapper extends BaseMapper<FileDto> {


}
