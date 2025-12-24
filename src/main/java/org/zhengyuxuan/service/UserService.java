package org.zhengyuxuan.service;
}
    boolean isUsernameExists(String username);
     */
     * @return true表示已存在
     * @param username 用户名
     * 检查用户名是否已存在
    /**

    User findByUsername(String username);
     */
     * @return 用户信息
     * @param username 用户名
     * 根据用户名查询用户
    /**

    User findById(Integer id);
     */
     * @return 用户信息
     * @param id 用户ID
     * 根据ID查询用户
    /**

    User login(String username, String password);
     */
     * @return 登录成功的用户，失败返回null
     * @param password 密码
     * @param username 用户名
     * 用户登录
    /**

    User register(String username, String password, String nickname);
     */
     * @return 注册成功的用户，失败返回null
     * @param nickname 昵称
     * @param password 密码
     * @param username 用户名
     * 用户注册
    /**

public interface UserService {
 */
 * 用户服务接口
/**

import org.zhengyuxuan.entity.User;


