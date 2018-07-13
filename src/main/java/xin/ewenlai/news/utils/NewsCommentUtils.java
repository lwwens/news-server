package xin.ewenlai.news.utils;

import org.jetbrains.annotations.NotNull;

/**
 * description : 新闻评论工具类，判断评论是否符合要求。
 *
 * @author lwwen
 * date : 2018-07-13 15:44
 * @version 0.0.3
 */
public class NewsCommentUtils {
    /**
     * 判断评论内容长度是否符合要求（ length <= 200）。
     *
     * @param content 评论内容
     * @return 结果
     */
    public static boolean ContentIsLong(@NotNull String content) {
        return content.codePointCount(0, content.length()) <= 200;
    }

    public static boolean IsURL(@NotNull String newsURL) {
        return true;
    }

}
