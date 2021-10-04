package top.lumos04.hotel.service.impl;

import top.lumos04.hotel.pojo.User;
import top.lumos04.hotel.mapper.UserMapper;
import top.lumos04.hotel.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zcb
 * @since 2021-08-28
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
