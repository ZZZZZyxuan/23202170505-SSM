package org.zhengyuxuan.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zhengyuxuan.entity.User;
import org.zhengyuxuan.mapper.UserMapper;
import org.zhengyuxuan.service.UserService;
import org.zhengyuxuan.util.PasswordUtil;

/**
 * 用户服务实现类
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    @Transactional
    public User register(String username, String password, String nickname) {
        // 检查用户名是否已存在
        if (isUsernameExists(username)) {
            return null;
        }

        // 创建用户对象
        User user = new User();
        user.setUsername(username);
        user.setPassword(PasswordUtil.md5(password)); // MD5加密密码
        user.setNickname(nickname != null ? nickname : username);
        user.setAvatar("/static/images/default-avatar.png");

        // 插入数据库
        int result = userMapper.insert(user);
        if (result > 0) {
            return user;
        }
        return null;
    }

    @Override
    public User login(String username, String password) {
        // 根据用户名查询用户
        User user = userMapper.selectByUsername(username);
        if (user == null) {
            return null;
        }

        // 验证密码
        if (PasswordUtil.matches(password, user.getPassword())) {
            return user;
        }
        return null;
    }

    @Override
    public User findById(Integer id) {
        return userMapper.selectById(id);
    }

    @Override
    public User findByUsername(String username) {
        return userMapper.selectByUsername(username);
    }

    @Override
    public boolean isUsernameExists(String username) {
        return userMapper.selectByUsername(username) != null;
    }
}

