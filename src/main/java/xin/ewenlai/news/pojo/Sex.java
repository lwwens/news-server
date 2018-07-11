package xin.ewenlai.news.pojo;

import java.util.Objects;

/**
 * description : 自定义性别的枚举类型，方便MySQL数据库相应字段的使用
 *
 * @author lwwen
 * date : 2018-07-11 17:11
 * @version 0.0.1
 */
public enum Sex {
    male("男"),
    female("女");

    private String value;

    Sex(String value) {
        this.value = value;
    }

    /**
     * 获取枚举类型{@link Sex Sex}对应的字符串。
     *
     * @return 枚举类型 Sex 对应的字符串。
     */
    public String getValue() {
        return value;
    }

    /**
     * 将字符串转化为相应的枚举类型{@link Sex Sex}的值。
     *
     * @param value 字符串转化为相应的枚举类型的值。
     * @return 枚举类型。
     */
    public static Sex formString(String value) {
        Objects.requireNonNull(value,"Value can not be bull in the sex !!!");
        Sex sex = null;
        if ("男".equals(value)) {
            sex = male;
        } else if ("女".equals(value)) {
            sex = female;
        }
        return sex;
    }
}
