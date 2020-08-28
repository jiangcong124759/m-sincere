package com.sg.zhsd.uav.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sg.zhsd.uav.config.CheckSensitiveWords;
import com.sg.zhsd.uav.data.dto.UserDto;
import com.sg.zhsd.uav.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,UserDto> implements IUserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public UserDto login(UserDto userDto) {

        UserDto userDto1 = userMapper.queryUserInfo(userDto);

        return userDto1;
    }

    @Override
    @CheckSensitiveWords(object = UserDto.class,field = {"username"})
    public String regist(UserDto userDto) {
        try {
            userDto.setId(UUID.randomUUID().toString());
            userMapper.regist(userDto);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<UserDto> getUserDtoList() {

        try {
            List<UserDto> userDtoList = userMapper.getUserDtoList();
            return userDtoList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public UserDto getUserInfo(String id) {
        UserDto userDto = userMapper.getUserInfo(id);
        return userDto;
    }

    @Override
    public void testCustomerZhujie(String content) {

    }

    @Override
    public void delete(String id) {
        userMapper.deleteByUserId(id);
    }
}
