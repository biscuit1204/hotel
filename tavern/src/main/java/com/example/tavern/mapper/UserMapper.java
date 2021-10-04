package com.example.tavern.mapper;

import com.example.tavern.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.tavern.pojo.vo.UserVo;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zcb
 * @since 2021-08-29
 */
public interface UserMapper extends BaseMapper<User> {

    List<UserVo> countNewUser();
}
