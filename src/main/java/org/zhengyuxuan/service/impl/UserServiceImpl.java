package org.zhengyuxuan.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zhengyuxuan.dao.UserMapper;
import org.zhengyuxuan.entity.User;
import org.zhengyuxuan.service.AppConstants;
import org.zhengyuxuan.service.PasswordUtil;
import org.zhengyuxuan.service.UserService;

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
        user.setPassword(PasswordUtil.md5(password));
        user.setNickname(nickname != null && !nickname.trim().isEmpty() ? nickname : username);
        user.setAvatar(AppConstants.DEFAULT_AVATAR);

        // 插入数据库
        return userMapper.insert(user) > 0 ? user : null;
    }

    @Override
    public User login(String username, String password) {
        User user = userMapper.selectByUsername(username);
        if (user != null && PasswordUtil.matches(password, user.getPassword())) {
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

