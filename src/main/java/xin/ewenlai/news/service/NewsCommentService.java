package xin.ewenlai.news.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xin.ewenlai.news.dao.NewsCommentDAO;
import xin.ewenlai.news.dao.NewsCommentLikeDAO;
import xin.ewenlai.news.dao.UserDAO;
import xin.ewenlai.news.pojo.NewsComment;
import xin.ewenlai.news.pojo.User;
import xin.ewenlai.news.utils.NewsCommentUtils;
import xin.ewenlai.news.utils.UserUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * description : 新闻评论服务，为控制器提供评论的 CRUP 服务。
 *
 * @author lwwen
 * date : 2018-07-12 17:14
 * @version 0.0.1
 */
@Service
public class NewsCommentService {
    private final NewsCommentDAO newsCommentDAO;

    private final UserDAO userDAO;

    private final NewsCommentLikeDAO likeDAO;

    @Autowired
    public NewsCommentService(NewsCommentDAO newsCommentDAO, UserDAO userDAO, NewsCommentLikeDAO likeDAO) {
        this.newsCommentDAO = newsCommentDAO;
        this.userDAO = userDAO;
        this.likeDAO = likeDAO;
    }

    /**
     * 从请求中获取用户名、评论内容及被评论新闻的 URL ，添加评论。
     *
     * @param request 请求
     * @return 评论成功或失败
     * @date 18-7-13
     * @time 下午5:35
     * @author lwwen
     */
    public boolean addComment(HttpServletRequest request) throws UnsupportedEncodingException {
        String username = request.getParameter("username");
        // 判断用户名是否符合要求，并判断是否存在
        if (username != null &&
                UserUtils.UsernameLengthIsRight(username) &&
                UserUtils.UsernameSymbolIsRight(username) &&
                userDAO.existsByName(username)) {
            NewsComment newsComment = new NewsComment();
            User user = new User();
            user.setName(username);
            newsComment.setUser(user);
            newsComment.setContent(URLDecoder.decode(request.getParameter("content"), "utf-8"));
            newsComment.setNewsURL(request.getParameter("newsURL"));
            newsComment.setTime(new Timestamp(new Date().getTime()));
            // 判断评论内容长度、新闻 URL 是否符合要求
            if (newsComment.getContent() != null &&
                    NewsCommentUtils.ContentIsLong(newsComment.getContent()) &&
                    NewsCommentUtils.IsURL(newsComment.getNewsURL())) {
                newsCommentDAO.save(newsComment);
                return true;
            }
        }
        return false;
    }

    /**
     * 通过新闻 URL 获取评论。
     *
     * @param newsURL 新闻链接
     * @return 返回评论数组
     * @date 18-7-13
     * @time 下午5:34
     * @author lwwen
     */
    public List<NewsComment> getComments(String newsURL) {
        if (NewsCommentUtils.IsURL(newsURL)) {
            List<NewsComment> comments = newsCommentDAO.findByNewsURL(newsURL);
            for (int i = 0; i < comments.size(); i++) {
                // 隐藏密码
                User user = comments.get(i).getUser();
                user.setPassword("******");
                comments.get(i).setUser(user);
                // 获取点赞数
                long likeCount = likeDAO.countByNewsComment(comments.get(i));
                comments.get(i).setLikeCount(likeCount);
            }
            return comments;
        }
        return null;
    }

    /**
     * 删除指定 id 的评论。
     *
     * @param id 评论 id
     * @return 删除评论成功或失败
     * @date 18-7-13
     * @time 下午5:37
     * @author lwwen
     */
    public boolean deleteCommentById(long id) {
        if (newsCommentDAO.existsById(id)) {
            newsCommentDAO.deleteById(id);
            return true;
        }
        return false;
    }

    /**
     * 根据用户名和新闻URL判断是否已经发表评论。
     *
     * @param username 用户名
     * @param newsURL  新闻URL
     * @return 是否发表
     * @date 18-7-16
     * @time 下午2:02
     * @author lwwen
     */
    public boolean existsByUsernameAndNewsURL(String username, String newsURL) {
        User user = new User();
        user.setName(username);
        return newsCommentDAO.existsByUserAndNewsURL(user, newsURL);
    }
}
