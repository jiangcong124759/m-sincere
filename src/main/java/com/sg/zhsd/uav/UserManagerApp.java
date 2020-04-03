package com.sg.zhsd.uav;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication(scanBasePackages = {"com.sg.zhsd.uav"})

@EntityScan("com.sg.zhsd.uav")
@EnableTransactionManagement //开启事务用的注解
@MapperScan(basePackages = {"com.sg.zhsd.uav.mapper"})
@EnableFeignClients("com.sg.bdyw.feign") // 开启Feign
@EnableCaching
public class UserManagerApp {
	public static void main(String[] args) {
		SpringApplication.run(UserManagerApp.class, args);
		System.out.println("用户管理服务成功启动!");
	}
}