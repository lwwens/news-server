package xin.ewenlai.news.pojo;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * 评论的点赞表。
 *
 * @author lwwen
 * @version 0.0.3
 * @create 2018-07-14 09:27
 */
@Entity
public class NewsCommentLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @ManyToOne
    private User user;

    @NotNull
    @ManyToOne
    private NewsComment newsComment;

    public NewsCommentLike() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public NewsComment getNewsComment() {
        return newsComment;
    }

    public void setNewsComment(NewsComment newsComment) {
        this.newsComment = newsComment;
    }
}
