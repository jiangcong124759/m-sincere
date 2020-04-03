package com.sg.zhsd.uav.service;

import com.sg.zhsd.uav.data.*;
import com.sg.zhsd.uav.data.dto.UserDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public interface IUserService {

    /**
     * 用户登录
     * @param userDto
     * @return
     */
    UserDto login(UserDto userDto);

    /**
     * 用户注册
     * @param userDto
     * @return
     */
    String regist(UserDto userDto);

    /**
     * 获取用户列表
     * @return
     */
    List<UserDto> getUserDtoList();

    /**
     * 查询用户详情
     * @param id
     * @return
     */
    UserDto getUserInfo(String id);
}
