package com.sg.zhsd.uav.mapper;

import com.sg.zhsd.uav.data.*;
import com.sg.zhsd.uav.data.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {

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
}
