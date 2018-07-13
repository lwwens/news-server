package xin.ewenlai.news.utils;

import org.jetbrains.annotations.NotNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * description : 用户工具类，提供验证用户字段的功能。
 *
 * @author lwwen
 * date : 2018-07-13 13:23
 * @version 0.0.3
 */
public class UserUtils {
    /**
     * 判断 username 长度是否符合要求（6 <= length <= 20）。
     *
     * @param username 用户名
     * @return 结果
     */
    public static boolean UsernameLengthIsRight(@NotNull String username) {
        return username.codePointCount(0, username.length()) >= 6 &&
                username.codePointCount(0, username.length()) <= 20;
    }

    /**
     * 判断 username 字符是否符合 Java 变量命名规则。
     *
     * @param username 用户名
     * @return 结果
     */
    public static boolean UsernameSymbolIsRight(@NotNull String username) {
        String usernameRegEx = "[a-zA-Z]{1}[a-zA-Z0-9_]";   // 跟 java 变量名规则一样
        Pattern usernamePattern = Pattern.compile(usernameRegEx);
        Matcher usernameMatcher = usernamePattern.matcher(username);
        return usernameMatcher.find();

    }

    /**
     * 判断 password 长度是否符合要求（6 <= length <= 20）。
     *
     * @param password 密码
     * @return 结果
     */
    public static boolean PasswordLengthIsRight(@NotNull String password) {
        return password.codePointCount(0, password.length()) >= 6 &&
                password.codePointCount(0, password.length()) <= 20;
    }

    /**
     * 判断 password 字符是否含有数字、英文字母或特殊符号其中的二者。
     *
     * @param password 密码
     * @return 结果
     */
    public static boolean PasswordSymbolIsRight(@NotNull String password) {
        int flag = 0;
        flag += password.matches(".*\\d+.*") ? 1 : 0;        // 判断是否含有数字
        flag += password.matches(".*[a-zA-Z]+.*") ? 1 : 0;   // 判断是否含有字母
        flag += password.matches(".*[~!@#$%^&*()_+|<>,.?/:;'\\[\\]{}\"]+.*") ? 1 : 0;   // 判断是否含有特殊符号
        return flag >= 2;
    }

    /**
     * 判断 nickname 长度是否符合要求（6 <= length <= 30）。
     *
     * @param nickname 昵称
     * @return 结果
     */
    public static boolean NicknameLengthIsRight(@NotNull String nickname) {
        return nickname.codePointCount(0, nickname.length()) >= 6 &&
                nickname.codePointCount(0, nickname.length()) <= 30;
    }

    public static boolean SexIsRight(String sex) {
        return "男".equals(sex) || "女".equals(sex);
    }
}
