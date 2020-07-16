package com.sg.zhsd.uav.controller;

import com.sg.bdyw.web.BaseController;
import com.sg.zhsd.uav.data.dto.UserDto;
import com.sg.zhsd.uav.service.IUserService;
import com.sg.zhsd.uav.utils.CodeImageUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Api(tags = {"用户管理"})
@RestController
@RequestMapping("/sincere/user")
@CrossOrigin(allowCredentials = "true") //允许跨域请求
@EnableAutoConfiguration
public class UserController extends BaseController {

    @Autowired
    IUserService userService;

    //登录验证码 code
    private static final String LOGIN_CODE = "LOGIN_CODE";

    @ApiOperation(value = "获取验证码")
    @GetMapping("code")
    public void genCodeImage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("image/jpeg");
        //禁止图像缓存
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        HttpSession session = request.getSession();
        CodeImageUtil imageUtil = new CodeImageUtil(120, 40, 4, 30);
        session.removeAttribute(LOGIN_CODE);
        session.setAttribute(LOGIN_CODE, imageUtil.getCode());
        System.out.println("user session id: {}" + request.getSession().getId());
        imageUtil.write(response.getOutputStream());
    }

    @ApiOperation(value = "用户登录")
    @PostMapping("login")
    public ResponseEntity<Object> login(@RequestBody UserDto userDto, HttpServletRequest request) {
        //校验验证码
        System.out.println("user session id: {}" + request.getSession().getId());
        HttpSession session = request.getSession();
        String session_code = (String) session.getAttribute(LOGIN_CODE);
        session.removeAttribute(LOGIN_CODE);
        System.out.println("session中的验证码:" + session_code);
        if (StringUtils.isBlank(userDto.getCode()) || !session_code.equalsIgnoreCase(userDto.getCode())) {
            return responseT("验证码错误");
        }
        UserDto result = userService.login(userDto);
        return responseT(result);
    }

    @ApiOperation(value = "用户注册")
    @PostMapping("regist")
    public ResponseEntity<Object> regist(@RequestBody UserDto userDto) {
        String result = userService.regist(userDto);
        return responseT(result);
    }

    @ApiOperation(value = "获取加班狗子用户列表")
    @GetMapping("/list/user")
    public ResponseEntity<List<UserDto>> getUserDtoList() {
        List<UserDto> resultList = userService.getUserDtoList();
        return responseT(resultList);
    }

    @ApiOperation(value = "查询成员信息")
    @GetMapping("/user/{id}")
    public ResponseEntity<UserDto> getUserInfo(@PathVariable String id) {
        UserDto result = userService.getUserInfo(id);
        return responseT(result);
    }

    /**
     * docker-compose测试
     */
    @GetMapping("/docker-compose")
    public String returnString() {
        return "docker-compose test!" + new Date();
    }

    /**
     * 自定义注解
     */
    @GetMapping("/customerZhujie")
    public String testCustomerZhujie(String content) {
        userService.testCustomerZhujie(content);
        return "";
    }

    public static void main(String[] args) {
        while (true) {
           int result =  add(1, 2);
            System.out.println(result);
        }
    }

    private static int add(int a, int b) {
        return a + b;
    }

}
