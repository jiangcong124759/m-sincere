package com.sg.zhsd.uav.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sg.zhsd.uav.data.dto.UserDto;

import java.util.List;

public interface IUserService extends IService<UserDto> {

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

    /**
     * 自定义注解Demo
     * @param content
     */
    void testCustomerZhujie(String content);

    /**
     * 删除
     * @param id
     */
    void delete(String id);
}
