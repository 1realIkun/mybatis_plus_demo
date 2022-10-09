package com.atguigu.mybatisplus;

import com.atguigu.mybatisplus.mapper.UserMapper;
import com.atguigu.mybatisplus.pojo.User;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
public class MybatisPlusWrapper {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void test01(){
        //查询用户名包含a，年龄在20到30之间，邮箱信息不为null的用户信息
        //SELECT uid AS id,user_name AS name,age,email,is_deleted FROM t_user WHERE is_deleted=0 AND (user_name LIKE ? AND age BETWEEN ? AND ? AND email IS NOT NULL)
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.like("user_name","a")
                .between("age",20,30)
                .isNotNull("email");
        List<User> list = userMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }

    @Test
    public void test02(){
        //查询用户信息，按照年龄降序排序，如果年龄相同，则按照id升序排序
        //SELECT uid AS id,user_name AS name,age,email,is_deleted FROM t_user WHERE is_deleted=0 ORDER BY age DESC,uid ASC
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.orderByDesc("age")
                .orderByAsc("uid");
        List<User> list = userMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }

    @Test
    public void test03(){

        //删除邮箱地址为null的用户信息
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.isNull("email");
        int result = userMapper.delete(queryWrapper);
        System.out.println(result);
    }

    @Test
    public void test04(){
        //将(年龄大于20并且用户名中包含有a)或邮箱为null的用户信息修改
      QueryWrapper<User> queryWrapper=new QueryWrapper<>();
      queryWrapper.gt("age",20)
              .like("user_name","小逼")
              .or()
              .isNull("email");
        User user = new User();
        user.setName("大逼");
        user.setEmail("wangsyDasb.@guigu.com");
        int result = userMapper.update(user, queryWrapper);
        System.out.println(result);
    }

    @Test
    public void test05(){
        //将用户名中包含有a并且（年龄大于20或邮箱为null）的用户信息修改
      QueryWrapper<User> queryWrapper=new QueryWrapper<>();
      queryWrapper.like("user_name","逼")
              .and(i->i.between("age",80,100).or().isNull("email"));
        User user = new User();
        user.setName("雪豹");
        user.setEmail("82137e87327@qq.com");
        int result= userMapper.update(user, queryWrapper);
        System.out.println(result);
    }

    @Test
    public void test06(){
      QueryWrapper<User> queryWrapper=new QueryWrapper<>();
      queryWrapper.select("user_name","age","email");
        List<Map<String, Object>> maps = userMapper.selectMaps(queryWrapper);
        maps.forEach(System.out::println);
    }

    @Test
    public void test07(){
        //查询id小于等于100的用户信息
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.inSql("uid","select uid from t_user where uid < 100");
        List<User> list = userMapper.selectList(queryWrapper);
        list.forEach(System.out::println);

    }

    @Test
    public void test08(){
        //将用户名中包含有a并且（年龄大于20或者邮箱为null）的用户信息修改
      UpdateWrapper<User> userUpdateWrapper=new UpdateWrapper<>();
      userUpdateWrapper.like("user_name","坤")
              .and(i->i.gt("age",20).or().isNull("email"));
      userUpdateWrapper.set("user_name","鸡你太美").set("age",45);
        int result = userMapper.update(null, userUpdateWrapper);
        System.out.println(result);
    }
    @Test
    public void test09(){
        String user_name="a";
        Integer age_bigin=null;
        Integer age_end=30;

        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        if (StringUtils.isNotBlank(user_name)){
            queryWrapper.like("user_name",user_name);
        }
        if (age_bigin!=null){
            queryWrapper.like("age",age_bigin);
        }
        if (age_end!=null){
            queryWrapper.like("age",age_end);
        }
        List<User> list = userMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }

    @Test
    public void test10(){
        String user_name="丁真";
        Integer ageBegin=null;
        Integer ageEnd=30;

        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(user_name),"user_name",user_name)
                .ge(ageBegin!=null,"age",ageBegin)
                .le(ageEnd!=null,"age",ageEnd);
        List<User> list = userMapper.selectList(queryWrapper);
        list.forEach(System.out::println);

    }

    @Test
    public void test(){
        String user_name="丁真";
        Integer ageBegin=null;
        Integer ageEnd=30;
       LambdaQueryWrapper<User> queryWrapper=new LambdaQueryWrapper<>();
       queryWrapper.like(StringUtils.isNotBlank(user_name),User::getName,user_name)
               .ge(ageBegin!=null,User::getAge,ageBegin)
               .le(ageEnd!=null,User::getAge,ageEnd);
        List<User> list = userMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }

    @Test
    public void test11(){
        //将用户名中包含有a并且（年龄大于20或者邮箱为null）的用户信息修改
     LambdaUpdateWrapper<User> updateWrapper=new LambdaUpdateWrapper<>();
     updateWrapper.like(User::getName,"雪")
             .and(i->i.ge(User::getAge,16).or().isNull(User::getEmail));
     updateWrapper.set(User::getName,"丁徐坤").set(User::getEmail,"abcdingzhen@163.com");
        int result = userMapper.update(null, updateWrapper);
        System.out.println(result);
    }


}
