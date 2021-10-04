package com.example.tavern.service;

import com.example.tavern.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.tavern.pojo.vo.UserVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zcb
 * @since 2021-08-29
 */
public interface UserService extends IService<User> {
    public List<UserVo> countNewUser();
}
