package com.sg.zhsd.uav.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sg.zhsd.uav.data.*;
import com.sg.zhsd.uav.data.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper extends BaseMapper<UserDto> {

    /**
     * 查询用户信息
     * @param userDto
     * @return
     */
    UserDto queryUserInfo(UserDto userDto);

    /**
     * 新增用户信息
     * @param userDto
     */
    void regist(UserDto userDto);

    /**
     * 查询用户列表
     * @return
     */
    List<UserDto> getUserDtoList();

    /**
     * 查询用户详情信息
     * @param id
     * @return
     */
    UserDto getUserInfo(String id);

    void deleteByUserId(String id);
}
