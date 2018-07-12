package xin.ewenlai.news.pojo;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

/**
 * description : 新闻的评论类
 *
 * @author lwwen
 * date : 2018-07-12 16:17
 * @version 0.0.1
 */
@Entity
public class NewsComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;

    @NotNull
    @ManyToOne
    private User user;

    @NotNull
    private Timestamp time;

    @NotNull
    @Size(max = 200)
    @Column(name = "newsURL")
    private String newsURL;

    @NotNull
    @Size(max = 200)
    private String content;

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
