package xin.ewenlai.news.utils;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;

/**
 * description : 枚举请求返回 JSON 数据中的代码。
 *
 * @author lwwen
 * date : 2018-07-13 13:22
 * @version 0.0.3
 */
public enum Code {
    SUCCESS(0),
    FAIL(1);

    private int value;

    Code(int value) {
        this.value = value;
    }

    /**
     * @param value 要转换为枚举类型的值
     * @return 转换结果
     */
    @Nullable
    @Contract(pure = true)
    public static Code valueOf(int value) {
        switch (value) {
            case 0:
                return SUCCESS;
            case 1:
                return FAIL;
            default:
                return null;
        }
    }

    /**
     * @return 返回枚举的值
     */
    @Contract(pure = true)
    public int getValue() {
        return value;
    }
}
