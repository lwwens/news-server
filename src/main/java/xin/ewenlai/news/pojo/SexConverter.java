package xin.ewenlai.news.pojo;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * description : 自定义性别的转换器
 *
 * @author lwwen
 * date : 2018-07-11 17:14
 * @version 0.0.1
 */
@Converter
public class SexConverter implements AttributeConverter<Sex, String> {
    /**
     * 将自定义的实体属性的枚举类型{@link Sex Sex}转化为字符串存储到 MySQL 数据库。
     *
     * @param sex 自定义的实体属性的枚举类型。
     * @return 实体属性对应的字符串。
     */
    @Override
    public String convertToDatabaseColumn(Sex sex) {
        return sex.getValue();
    }

    /**
     * 将 MySQL 数据库中的值转化为实体属性的枚举类型{@link Sex Sex}。
     *
     * @param s MySQL数据库字段保存的类型。
     * @return 实体属性的枚举类型。
     */
    @Override
    public Sex convertToEntityAttribute(String s) {
        return Sex.formString(s);
    }
}
