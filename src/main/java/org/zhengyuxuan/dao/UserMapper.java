package org.zhengyuxuan.dao;

import org.apache.ibatis.annotations.Param;
import org.zhengyuxuan.entity.User;

public interface UserMapper { // 用户数据访问接口

    User selectById(@Param("id") Integer id); // 根据ID查询

    User selectByUsername(@Param("username") String username); // 根据用户名查询

    int insert(User user); // 插入用户

    int update(User user); // 更新用户
}

