package xin.ewenlai.news.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xin.ewenlai.news.dao.NewsCommentDAO;
import xin.ewenlai.news.dao.NewsCommentLikeDAO;
import xin.ewenlai.news.dao.UserDAO;
import xin.ewenlai.news.pojo.NewsComment;
import xin.ewenlai.news.pojo.NewsCommentLike;
import xin.ewenlai.news.pojo.User;

import javax.servlet.http.HttpServletRequest;

/**
 * @author lwwen
 * @version 0.0.0
 * @create 2018-07-14 10:02
 */
@Service
public class NewsCommentLikeService {
    private final NewsCommentLikeDAO likeDAO;
    private final UserDAO userDAO;
    private final NewsCommentDAO newsCommentDAO;

    @Autowired
    public NewsCommentLikeService(NewsCommentLikeDAO likeDAO, UserDAO userDAO, NewsCommentDAO newsCommentDAO) {
        this.likeDAO = likeDAO;
        this.userDAO = userDAO;
        this.newsCommentDAO = newsCommentDAO;
    }

    /**
     * 添加某评论的点赞。
     *
     * @param request 请求
     * @return 点赞成功或失败
     * @date 18-7-14
     * @time 上午11:23
     * @author lwwen
     */
    public boolean addLike(HttpServletRequest request) {
        NewsCommentLike like = new NewsCommentLike();
        User user = new User();
        user.setName(request.getParameter("username"));
        like.setUser(user);
        NewsComment newsComment = new NewsComment();
        newsComment.setId(Long.parseLong(request.getParameter("commentId")));
        like.setNewsComment(newsComment);
        if (userDAO.existsByName(user.getName()) &&
                newsCommentDAO.existsById(newsComment.getId())) {
            likeDAO.save(like);
            return true;
        }
        return false;
    }


    /**
     * 判断某人是否为某评论点赞。
     *
     * @param username  用户名
     * @param commentId 评论Id
     * @return 点赞与否
     * @date 18-7-14
     * @time 上午11:24
     * @author lwwen
     */
    public boolean existsByUsernameAndCommentId(String username, long commentId) {
        User user = new User();
        user.setName(username);
        NewsComment newsComment = new NewsComment();
        newsComment.setId(commentId);
        return likeDAO.findByUserAndCommentId(user, newsComment) != null;
    }

}
