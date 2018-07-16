package xin.ewenlai.news.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import xin.ewenlai.news.pojo.NewsComment;
import xin.ewenlai.news.pojo.User;

import java.util.List;

/**
 * description : 访问新闻评论表的接口。
 *
 * @author lwwen
 * date : 2018-07-12 17:16
 * @version 0.0.1
 */
public interface NewsCommentDAO extends JpaRepository<NewsComment, Long> {
    List<NewsComment> findByNewsURL(String newsURL);

    boolean existsByUserAndNewsURL(User user, String newsURL);
}