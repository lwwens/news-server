package xin.ewenlai.news.utils;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;

/**
 * description : 枚举请求返回 JSON 数据中的代码。
 *
 * @author lwwen
 * date : 2018-07-12 08:46
 * @version 0.0.1
 */
public enum Code {
    Success(0),
    UsernameLengthIsWrong(101),
    UsernameSymbolIsWrong(102),
    PasswordLengthIsWrong(103),
    PasswordSymbolIsWrong(104),
    NicknameLengthIsWrong(105),
    SexValueIsWrong(106),
    UserIsNotExists(107),
    UserIsExists(108),
    CommentIsLong(201),
    CommentIDNotExists(301);

    private int value;

    Code(int value) {
        this.value = value;
    }

    @Nullable
    @Contract(pure = true)
    public static Code valueOf(int value) {
        switch (value) {
            case 0:
                return Success;
            case 101:
                return UsernameLengthIsWrong;
            case 102:
                return UsernameSymbolIsWrong;
            case 103:
                return PasswordLengthIsWrong;
            case 104:
                return PasswordSymbolIsWrong;
            case 105:
                return NicknameLengthIsWrong;
            case 106:
                return SexValueIsWrong;
            case 107:
                return UserIsNotExists;
            case 108:
                return UserIsExists;
            case 201:
                return CommentIsLong;
            case 301:
                return CommentIDNotExists;
            default:
                return null;
        }
    }

    @Contract(pure = true)
    public int getValue() {
        return value;
    }
}
