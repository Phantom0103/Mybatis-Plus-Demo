package com.wenxia.mbpd.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wenxia.mbpd.entity.User;
import com.wenxia.mbpd.mapper.UserMapper;
import com.wenxia.mbpd.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * @Author zhouw
 * @Description
 * @Date 2020-01-06
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
}
