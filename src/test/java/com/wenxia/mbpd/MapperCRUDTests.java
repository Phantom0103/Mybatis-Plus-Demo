package com.wenxia.mbpd;

import com.wenxia.mbpd.entity.User;
import com.wenxia.mbpd.mapper.UserMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author zhou
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MapperCRUDTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelectAll() {
        System.out.println("-------- selectAll method test ---------");
        List<User> userList = userMapper.selectList(null);
        userList.forEach(System.out::println);
    }

    @Test
    public void testInsert() {
        System.out.println("-------- insert method test ---------");
        User user = new User();
        user.setId(System.currentTimeMillis());
        user.setName("Zhang");
        user.setAge(26);
        user.setEmail("zhang123@foxmail.com");
        int r = userMapper.insert(user);
        Assert.assertEquals(1, r);
    }

    @Test
    public void testDelete() {
        System.out.println("-------- delete method test ---------");
        int r = userMapper.deleteById(1);
        Assert.assertEquals(1, r);
    }

    @Test
    public void testUpdate() {
        System.out.println("-------- delete method test ---------");
        User user = new User();
        user.setId(2L);
        user.setName("weiken");
        int r = userMapper.updateById(user);
        Assert.assertEquals(1, r);
    }

    @Test
    public void testSelect() {
        System.out.println("-------- select method test ---------");
        User user = userMapper.selectById(2L);
        System.out.println(user);
    }
}
