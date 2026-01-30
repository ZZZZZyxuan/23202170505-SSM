package org.zhengyuxuan.service;

public final class ValidationUtil {

    private ValidationUtil() {
    }

    public static boolean isBlank(String str) { // 判断字符串是否为空
        return str == null || str.trim().isEmpty();
    }

    public static boolean isNotBlank(String str) { // 判断字符串是否非空
        return !isBlank(str);
    }

    public static String validateUsername(String username) { // 校验用户名
        if (isBlank(username)) {
            return AppConstants.ERR_USERNAME_EMPTY;
        }
        int len = username.trim().length();
        if (len < AppConstants.USERNAME_MIN_LENGTH || len > AppConstants.USERNAME_MAX_LENGTH) {
            return AppConstants.ERR_USERNAME_LENGTH;
        }
        return null; // 校验通过
    }

    public static String validatePassword(String password) { // 校验密码
        if (isBlank(password)) {
            return AppConstants.ERR_PASSWORD_EMPTY;
        }
        if (password.length() < AppConstants.PASSWORD_MIN_LENGTH) {
            return AppConstants.ERR_PASSWORD_LENGTH;
        }
        return null; // 校验通过
    }

    public static String validateRating(Integer rating) { // 校验评分
        if (rating == null || rating < AppConstants.RATING_MIN || rating > AppConstants.RATING_MAX) {
            return AppConstants.ERR_RATING_INVALID;
        }
        return null; // 校验通过
    }

    public static String validateReviewContent(String content) { // 校验评论内容
        if (isBlank(content)) {
            return AppConstants.ERR_CONTENT_EMPTY;
        }
        if (content.length() > AppConstants.REVIEW_CONTENT_MAX_LENGTH) {
            return AppConstants.ERR_CONTENT_TOO_LONG;
        }
        return null; // 校验通过
    }

    public static String emptyToNull(String str) { // 空字符串转null
        return isBlank(str) ? null : str.trim();
    }
}

