package com.wenxia.mbpd;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wenxia.mbpd.entity.User;
import com.wenxia.mbpd.mapper.UserMapper;
import com.wenxia.mbpd.service.IUserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Author zhouw
 * @Description
 * @Date 2020-01-06
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceCRUDTests {

    @Autowired
    private IUserService userService;

    @Test
    public void testSave() {
        User user = new User();
        user.setId(System.currentTimeMillis());
        user.setName("feng");
        user.setAge(28);
        user.setEmail("feng@125.com");
        userService.save(user);
    }

    @Test
    public void testSaveBatch() {
        List<User> users = new ArrayList<>();
        User user1 = new User();
        user1.setId(System.currentTimeMillis());
        user1.setName("yunoka");
        user1.setAge(26);
        user1.setEmail("asa@126.com");
        users.add(user1);

        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }

        User user2 = new User();
        user2.setId(System.currentTimeMillis());
        user2.setName("asasa");
        user2.setAge(16);
        user2.setEmail("sqd@ca.com");
        users.add(user2);

        userService.saveBatch(users);
    }

    @Test
    public void testSaveBatchSize() {
        List<User> users = new ArrayList<>();
        User user1 = new User();
        user1.setId(System.currentTimeMillis());
        user1.setName("yunoka");
        user1.setAge(26);
        user1.setEmail("asa@126.com");
        users.add(user1);

        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }

        User user2 = new User();
        user2.setId(System.currentTimeMillis());
        user2.setName("asasa");
        user2.setAge(16);
        user2.setEmail("sqd@ca.com");
        users.add(user2);

        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }

        User user3 = new User();
        user3.setId(System.currentTimeMillis());
        user3.setName("kkkk");
        user3.setAge(16);
        user3.setEmail("sqd@ca.com");
        users.add(user3);

        userService.saveBatch(users, 2);
    }

    @Test
    public void testSaveOrUpdate() {
        User user = new User();
        user.setId(1578048519194L);
        user.setName("Okma");
        userService.saveOrUpdate(user);
    }

    @Test
    public void testRemoveById() {
        userService.removeById(2);
    }

    @Test
    public void testRemove() {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.le("id", 1000);
        userService.remove(queryWrapper);
    }

    @Test
    public void testGet() {
        User user1 = userService.getById(1578048519194L);
        System.out.println(user1);

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.le("id", 1578048519194L);
        User user2 = userService.getOne(queryWrapper);
        System.out.println(user2);

        Map<String, Object> map = userService.getMap(queryWrapper);
        map.forEach((k, v) -> System.out.println(k + " = " + v));
    }

    @Test
    public void testGetOne() {
        QueryWrapper queryWrapper = new QueryWrapper();
        User user = userService.getOne(new QueryWrapper<User>().eq("name", "asasa"), false);
    }

    @Test
    public void getObj() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", "asasa");
        queryWrapper.last("limit 1");
//        JSONObject jo = userService.getObj(queryWrapper, e -> {
//            User user = (User) e;
//            JSONObject o = new JSONObject();
//            o.put("id", user.getId());
//            o.put("username", user.getName());
//            return o;
//        });
//        System.out.println(jo);
        LocalDateTime date = userService.getObj(queryWrapper, e -> {
            Instant instant = Instant.ofEpochMilli((Long) e);
            return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        });
        System.out.println(date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }

    @Test
    public void testList() {
        List<User> users = userService.list();
        users.forEach(System.out::println);

        List<Object> list = userService.listObjs();
        list.forEach(System.out::println);
    }

    @Test
    public void testPage() {
        Page page = new Page(2, 3);
        userService.page(page);
        System.out.println("size: " + page.getSize());
        System.out.println("total: " + page.getTotal());
        System.out.println("hasPrevious: " + page.hasPrevious());
        System.out.println("hasNext: " + page.hasNext());
        page.getRecords().forEach(System.out::println);
    }

    @Test
    public void testSql() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("id", 1578048519194L);
        UserMapper mapper = (UserMapper) userService.getBaseMapper();
        User user = mapper.getOneBySql(wrapper);
        System.out.println(wrapper.getCustomSqlSegment());
        System.out.println(user);
    }
}
