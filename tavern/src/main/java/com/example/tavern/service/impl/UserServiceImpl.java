package com.example.tavern.service.impl;

import com.example.tavern.pojo.User;
import com.example.tavern.mapper.UserMapper;
import com.example.tavern.pojo.vo.UserVo;
import com.example.tavern.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zcb
 * @since 2021-08-29
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<UserVo> countNewUser() {
        return userMapper.countNewUser();
    }
}
