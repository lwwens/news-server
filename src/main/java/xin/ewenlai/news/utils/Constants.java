package xin.ewenlai.news.utils;

import org.jetbrains.annotations.NotNull;

import java.util.UUID;

/**
 * @author lwwen
 * @version 0.0.0
 * @create 2018-07-16 17:14
 */
public class Constants {
    @NotNull
    public static String getUUID32() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
