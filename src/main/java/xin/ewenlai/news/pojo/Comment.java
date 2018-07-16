package xin.ewenlai.news.pojo;

import java.sql.Timestamp;

/**
 * 请求返回的评论类.
 *
 * @author lwwen
 * @version 0.0.0
 * @create 2018-07-16 08:51
 */
public class Comment {
    private long id;

    private String username;

    private Timestamp time;

    private String newsURL;

    private String content;

    public Comment(long id, String username,
                   Timestamp time, String newsURL,
                   String content) {
        this.id = id;
        this.username = username;
        this.time = time;
        this.newsURL = newsURL;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public String getNewsURL() {
        return newsURL;
    }

    public void setNewsURL(String newsURL) {
        this.newsURL = newsURL;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
