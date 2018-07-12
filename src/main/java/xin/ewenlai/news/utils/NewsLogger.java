package xin.ewenlai.news.utils;

import java.util.logging.Logger;

/**
 * description : 打印日志。
 *
 * @author lwwen
 * date : 2018-07-12 13:17
 * @version 0.0.1
 */
public class NewsLogger {
    private static Logger logger = Logger.getLogger("NewsLogger");

    /**
     * 打印 INFO 级别的日志。
     *
     * @param msg 日志消息。
     */
    public static void info(String msg) {
        logger.info(msg);
    }

    /**
     * 打印 WARNING 级别的日志。
     *
     * @param msg 日志消息。
     */
    public static void warning(String msg) {
        logger.warning(msg);
    }
}
