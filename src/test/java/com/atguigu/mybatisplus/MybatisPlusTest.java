package com.atguigu.mybatisplus;

import com.atguigu.mybatisplus.mapper.UserMapper;
import com.atguigu.mybatisplus.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class MybatisPlusTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void test(){
        List<User> userList = userMapper.selectList(null);
        userList.forEach(System.out::println);

    }
    @Test
    public void insert(){
        User user = new User();
        user.setName("王世宇");
        user.setAge(26);
        user.setEmail("123456@qq.com");
        int result = userMapper.insert(user);
        System.out.println("result:"+result);
        System.out.println("id:"+user.getId());
    }

    @Test
    public void deleteById(){
        //通过id删除用户信息
        int result = userMapper.deleteById(1572481034489012226L);
        System.out.println(result);
    }

    @Test
    public void deleteByMap(){
        //根据map集合中设置条件来删除
        Map<String,Object> map=new HashMap<>();
        map.put("name","王世宇");
        map.put("age",27);
        int result = userMapper.deleteByMap(map);
        System.out.println(result);
    }

    @Test
    public void deleteBatch(){
        List<Long> longs = Arrays.asList(1L, 2L);
        int i = userMapper.deleteBatchIds(longs);
        System.out.println(i);
    }

    @Test
    public void update(){
        User user = new User();
        user.setId(5L);
        user.setName("小母牛");
        userMapper.updateById(user);
    }

    @Test
    public void selectById(){
        User user = userMapper.selectById(5l);
        System.out.println(user);
    }

    @Test
    public void selectBatch(){
        List<Long> longs = Arrays.asList(1l, 2l, 3l, 4l);
        List<User> users = userMapper.selectBatchIds(longs);
        users.forEach(System.out::println);
    }

    @Test
    public void selectMap(){
        Map<String,Object> map=new HashMap<>();
        map.put("name","小母牛");
        map.put("age",27);
        List<User> users = userMapper.selectByMap(map);
        users.forEach(System.out::println);
        List<User> userList = userMapper.selectList(null);
        userList.forEach(System.out::println);
    }
}
