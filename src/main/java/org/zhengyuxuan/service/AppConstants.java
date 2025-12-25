package org.zhengyuxuan.service;

/**
 * 应用常量类
 * 集中管理项目中使用的常量
 */
public final class AppConstants {

    private AppConstants() {
        // 防止实例化
    }

    // ==================== 用户相关常量 ====================
    /** 用户名最小长度 */
    public static final int USERNAME_MIN_LENGTH = 3;
    /** 用户名最大长度 */
    public static final int USERNAME_MAX_LENGTH = 20;
    /** 密码最小长度 */
    public static final int PASSWORD_MIN_LENGTH = 6;
    /** 默认头像路径 */
    public static final String DEFAULT_AVATAR = "/static/images/default-avatar.png";
    /** Session中当前用户的key */
    public static final String SESSION_CURRENT_USER = "currentUser";

    // ==================== 评论相关常量 ====================
    /** 评论内容最大长度 */
    public static final int REVIEW_CONTENT_MAX_LENGTH = 500;
    /** 评分最小值 */
    public static final int RATING_MIN = 1;
    /** 评分最大值 */
    public static final int RATING_MAX = 5;

    // ==================== 响应状态码 ====================
    /** 成功 */
    public static final int CODE_SUCCESS = 200;
    /** 未授权 */
    public static final int CODE_UNAUTHORIZED = 401;
    /** 服务器错误 */
    public static final int CODE_ERROR = 500;

    // ==================== 响应消息 ====================
    public static final String MSG_SUCCESS = "操作成功";
    public static final String MSG_LOGIN_SUCCESS = "登录成功";
    public static final String MSG_LOGOUT_SUCCESS = "登出成功";
    public static final String MSG_REGISTER_SUCCESS = "注册成功";
    public static final String MSG_REVIEW_SUCCESS = "评论成功";
    public static final String MSG_DELETE_SUCCESS = "删除成功";
    public static final String MSG_UNAUTHORIZED = "请先登录";
    public static final String MSG_SYSTEM_ERROR = "系统繁忙，请稍后重试";

    // ==================== 错误消息 ====================
    public static final String ERR_USERNAME_EMPTY = "用户名不能为空";
    public static final String ERR_PASSWORD_EMPTY = "密码不能为空";
    public static final String ERR_USERNAME_LENGTH = "用户名长度应为3-20个字符";
    public static final String ERR_PASSWORD_LENGTH = "密码长度不能少于6个字符";
    public static final String ERR_USERNAME_EXISTS = "用户名已存在";
    public static final String ERR_LOGIN_FAILED = "用户名或密码错误";
    public static final String ERR_REGISTER_FAILED = "注册失败，请稍后重试";
    public static final String ERR_MOVIE_NOT_FOUND = "电影不存在";
    public static final String ERR_MOVIE_ID_EMPTY = "电影ID不能为空";
    public static final String ERR_RATING_INVALID = "评分必须在1-5之间";
    public static final String ERR_CONTENT_EMPTY = "评论内容不能为空";
    public static final String ERR_CONTENT_TOO_LONG = "评论内容不能超过500字";
    public static final String ERR_REVIEW_FAILED = "评论失败，请稍后重试";
    public static final String ERR_DELETE_FAILED = "删除失败，可能没有权限";
}

