package org.zhengyuxuan.entity;
}
    }
                '}';
                ", createTime=" + createTime +
                ", avatar='" + avatar + '\'' +
                ", nickname='" + nickname + '\'' +
                ", username='" + username + '\'' +
                "id=" + id +
        return "User{" +
    public String toString() {
    @Override

    }
        this.createTime = createTime;
    public void setCreateTime(Date createTime) {

    }
        return createTime;
    public Date getCreateTime() {

    }
        this.avatar = avatar;
    public void setAvatar(String avatar) {

    }
        return avatar;
    public String getAvatar() {

    }
        this.nickname = nickname;
    public void setNickname(String nickname) {

    }
        return nickname;
    public String getNickname() {

    }
        this.password = password;
    public void setPassword(String password) {

    }
        return password;
    public String getPassword() {

    }
        this.username = username;
    public void setUsername(String username) {

    }
        return username;
    public String getUsername() {

    }
        this.id = id;
    public void setId(Integer id) {

    }
        return id;
    public Integer getId() {

    }
        this.nickname = nickname;
        this.password = password;
        this.username = username;
    public User(String username, String password, String nickname) {

    }
    public User() {

    private Date createTime;
    /** 注册时间 */

    private String avatar;
    /** 头像 */

    private String nickname;
    /** 昵称 */

    private String password;
    /** 密码 */

    private String username;
    /** 用户名 */

    private Integer id;
    /** 用户ID */

    private static final long serialVersionUID = 1L;

public class User implements Serializable {
 */
 * 对应数据库表 t_user
 * 用户实体类
/**

import java.util.Date;
import java.io.Serializable;


