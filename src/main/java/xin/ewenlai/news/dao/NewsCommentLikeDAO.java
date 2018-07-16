package xin.ewenlai.news.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import xin.ewenlai.news.pojo.NewsComment;
import xin.ewenlai.news.pojo.NewsCommentLike;
import xin.ewenlai.news.pojo.User;

/**
 * 评论点赞接口。
 *
 * @author lwwen
 * @version 0.0.3
 * @create 2018-07-14 09:59
 */
public interface NewsCommentLikeDAO extends JpaRepository<NewsCommentLike, Long> {
    @Query("select cmtlike from NewsCommentLike cmtlike where cmtlike.user = ?1 and cmtlike.newsComment = ?2")
    NewsCommentLike findByUserAndComment(User user, NewsComment newsComment);

    @Transactional
    @Modifying
    @Query("delete from NewsCommentLike where user = ?1 and newsComment = ?2")
    void deleteByUserAndComment(User user, NewsComment newsComment);

    long countByNewsComment(NewsComment newsComment);
}