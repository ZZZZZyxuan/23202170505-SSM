package org.zhengyuxuan.service;

import org.zhengyuxuan.entity.User;

public interface UserService { // 用户服务接口

    User register(String username, String password, String nickname); // 用户注册

    User login(String username, String password); // 用户登录

    User findById(Integer id); // 根据ID查询

    User findByUsername(String username); // 根据用户名查询

    boolean isUsernameExists(String username); // 检查用户名是否存在
}

