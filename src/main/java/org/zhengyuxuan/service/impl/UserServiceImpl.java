package org.zhengyuxuan.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zhengyuxuan.dao.UserMapper;
import org.zhengyuxuan.entity.User;
import org.zhengyuxuan.service.AppConstants;
import org.zhengyuxuan.service.PasswordUtil;
import org.zhengyuxuan.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    @Transactional
    public User register(String username, String password, String nickname) { // 用户注册
        if (isUsernameExists(username)) { // 检查用户名是否存在
            return null;
        }

        User user = new User(); // 创建用户对象
        user.setUsername(username);
        user.setPassword(PasswordUtil.md5(password)); // MD5加密密码
        user.setNickname(nickname != null && !nickname.trim().isEmpty() ? nickname : username); // 设置昵称
        user.setAvatar(AppConstants.DEFAULT_AVATAR); // 设置默认头像

        return userMapper.insert(user) > 0 ? user : null; // 插入数据库
    }

    @Override
    public User login(String username, String password) { // 用户登录
        User user = userMapper.selectByUsername(username); // 查询用户
        if (user != null && PasswordUtil.matches(password, user.getPassword())) { // 验证密码
            return user;
        }
        return null;
    }

    @Override
    public User findById(Integer id) { // 根据ID查询用户
        return userMapper.selectById(id);
    }

    @Override
    public User findByUsername(String username) { // 根据用户名查询用户
        return userMapper.selectByUsername(username);
    }

    @Override
    public boolean isUsernameExists(String username) { // 检查用户名是否存在
        return userMapper.selectByUsername(username) != null;
    }
}

