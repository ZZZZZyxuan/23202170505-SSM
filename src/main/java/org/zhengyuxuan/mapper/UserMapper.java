package org.zhengyuxuan.mapper;
}
    int update(User user);
     */
     * @return 影响行数
     * @param user 用户信息
     * 更新用户信息
    /**

    int insert(User user);
     */
     * @return 影响行数
     * @param user 用户信息
     * 插入新用户
    /**

    User selectByUsername(@Param("username") String username);
     */
     * @return 用户信息
     * @param username 用户名
     * 根据用户名查询用户
    /**

    User selectById(@Param("id") Integer id);
     */
     * @return 用户信息
     * @param id 用户ID
     * 根据ID查询用户
    /**

public interface UserMapper {
 */
 * 用户Mapper接口
/**

import org.zhengyuxuan.entity.User;
import org.apache.ibatis.annotations.Param;


