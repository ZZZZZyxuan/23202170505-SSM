package org.zhengyuxuan.service;

/**
 * 参数校验工具类
 * 提供通用的参数校验方法
 */
public final class ValidationUtil {

    private ValidationUtil() {
        // 防止实例化
    }

    /**
     * 判断字符串是否为空或空白
     * @param str 待检查的字符串
     * @return true表示为空或空白
     */
    public static boolean isBlank(String str) {
        return str == null || str.trim().isEmpty();
    }

    /**
     * 判断字符串是否不为空且非空白
     * @param str 待检查的字符串
     * @return true表示不为空且非空白
     */
    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

    /**
     * 校验用户名格式
     * @param username 用户名
     * @return 错误消息，null表示校验通过
     */
    public static String validateUsername(String username) {
        if (isBlank(username)) {
            return AppConstants.ERR_USERNAME_EMPTY;
        }
        int len = username.trim().length();
        if (len < AppConstants.USERNAME_MIN_LENGTH || len > AppConstants.USERNAME_MAX_LENGTH) {
            return AppConstants.ERR_USERNAME_LENGTH;
        }
        return null;
    }

    /**
     * 校验密码格式
     * @param password 密码
     * @return 错误消息，null表示校验通过
     */
    public static String validatePassword(String password) {
        if (isBlank(password)) {
            return AppConstants.ERR_PASSWORD_EMPTY;
        }
        if (password.length() < AppConstants.PASSWORD_MIN_LENGTH) {
            return AppConstants.ERR_PASSWORD_LENGTH;
        }
        return null;
    }

    /**
     * 校验评分
     * @param rating 评分值
     * @return 错误消息，null表示校验通过
     */
    public static String validateRating(Integer rating) {
        if (rating == null || rating < AppConstants.RATING_MIN || rating > AppConstants.RATING_MAX) {
            return AppConstants.ERR_RATING_INVALID;
        }
        return null;
    }

    /**
     * 校验评论内容
     * @param content 评论内容
     * @return 错误消息，null表示校验通过
     */
    public static String validateReviewContent(String content) {
        if (isBlank(content)) {
            return AppConstants.ERR_CONTENT_EMPTY;
        }
        if (content.length() > AppConstants.REVIEW_CONTENT_MAX_LENGTH) {
            return AppConstants.ERR_CONTENT_TOO_LONG;
        }
        return null;
    }

    /**
     * 将空字符串转换为null
     * @param str 原字符串
     * @return 处理后的字符串
     */
    public static String emptyToNull(String str) {
        return isBlank(str) ? null : str.trim();
    }
}

