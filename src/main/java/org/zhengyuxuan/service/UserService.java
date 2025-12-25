package org.zhengyuxuan.service;

import org.zhengyuxuan.entity.User;

/**
 * 用户服务接口
 */
public interface UserService {

    /**
     * 用户注册
     * @param username 用户名
     * @param password 密码
     * @param nickname 昵称
     * @return 注册成功的用户，失败返回null
     */
    User register(String username, String password, String nickname);

    /**
     * 用户登录
     * @param username 用户名
     * @param password 密码
     * @return 登录成功的用户，失败返回null
     */
    User login(String username, String password);

    /**
     * 根据ID查询用户
     * @param id 用户ID
     * @return 用户信息
     */
    User findById(Integer id);

    /**
     * 根据用户名查询用户
     * @param username 用户名
     * @return 用户信息
     */
    User findByUsername(String username);

    /**
     * 检查用户名是否已存在
     * @param username 用户名
     * @return true表示已存在
     */
    boolean isUsernameExists(String username);
}

