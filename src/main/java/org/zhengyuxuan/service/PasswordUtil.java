package org.zhengyuxuan.service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class PasswordUtil {

    private static final char[] HEX_CHARS = "0123456789abcdef".toCharArray(); // 十六进制字符数组

    private PasswordUtil() {
    }

    public static String md5(String password) { // MD5加密方法
        if (password == null || password.isEmpty()) { // 参数校验
            throw new IllegalArgumentException("密码不能为空");
        }
        try {
            MessageDigest md = MessageDigest.getInstance("MD5"); // 获取MD5实例
            byte[] digest = md.digest(password.getBytes(StandardCharsets.UTF_8)); // 加密
            return bytesToHex(digest); // 转换为十六进制
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5加密失败", e);
        }
    }

    public static boolean matches(String rawPassword, String encodedPassword) { // 密码匹配验证
        if (rawPassword == null || encodedPassword == null) { // 参数校验
            return false;
        }
        return md5(rawPassword).equals(encodedPassword); // 比较加密后的密码
    }

    private static String bytesToHex(byte[] bytes) { // 字节数组转十六进制字符串
        char[] hexChars = new char[bytes.length * 2]; // 创建字符数组
        for (int i = 0; i < bytes.length; i++) {
            int v = bytes[i] & 0xFF; // 转换为无符号整数
            hexChars[i * 2] = HEX_CHARS[v >>> 4]; // 高4位
            hexChars[i * 2 + 1] = HEX_CHARS[v & 0x0F]; // 低4位
        }
        return new String(hexChars); // 返回字符串
    }
}

