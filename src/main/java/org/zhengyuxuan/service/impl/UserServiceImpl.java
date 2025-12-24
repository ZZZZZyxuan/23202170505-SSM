package org.zhengyuxuan.service.impl;
}
    }
        return userMapper.selectByUsername(username) != null;
    public boolean isUsernameExists(String username) {
    @Override

    }
        return userMapper.selectByUsername(username);
    public User findByUsername(String username) {
    @Override

    }
        return userMapper.selectById(id);
    public User findById(Integer id) {
    @Override

    }
        return null;
        }
            return user;
        if (PasswordUtil.matches(password, user.getPassword())) {
        // 验证密码

        }
            return null;
        if (user == null) {
        User user = userMapper.selectByUsername(username);
        // 根据用户名查询用户
    public User login(String username, String password) {
    @Override

    }
        return null;
        }
            return user;
        if (result > 0) {
        int result = userMapper.insert(user);
        // 插入数据库

        user.setAvatar("/static/images/default-avatar.png");
        user.setNickname(nickname != null ? nickname : username);
        user.setPassword(PasswordUtil.md5(password)); // MD5加密密码
        user.setUsername(username);
        User user = new User();
        // 创建用户对象

        }
            return null;
        if (isUsernameExists(username)) {
        // 检查用户名是否已存在
    public User register(String username, String password, String nickname) {
    @Transactional
    @Override

    private UserMapper userMapper;
    @Autowired

public class UserServiceImpl implements UserService {
@Service
 */
 * 用户服务实现类
/**

import org.zhengyuxuan.util.PasswordUtil;
import org.zhengyuxuan.service.UserService;
import org.zhengyuxuan.mapper.UserMapper;
import org.zhengyuxuan.entity.User;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;


