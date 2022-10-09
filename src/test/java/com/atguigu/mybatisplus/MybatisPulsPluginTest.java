package com.atguigu.mybatisplus;

import com.atguigu.mybatisplus.mapper.UserMapper;
import com.atguigu.mybatisplus.pojo.User;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MybatisPulsPluginTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void test01(){
        Page<User> page=new Page<>(2,3);
        userMapper.selectPage(page,null);
        System.out.println(page);
        System.out.println("奥里给");
        System.out.println("奥里给第二次");
    }
}
