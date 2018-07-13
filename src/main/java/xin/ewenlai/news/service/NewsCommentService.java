package xin.ewenlai.news.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xin.ewenlai.news.dao.NewsCommentDAO;
import xin.ewenlai.news.dao.UserDAO;
import xin.ewenlai.news.pojo.NewsComment;
import xin.ewenlai.news.pojo.User;
import xin.ewenlai.news.utils.NewsCommentUtils;
import xin.ewenlai.news.utils.NewsLogger;
import xin.ewenlai.news.utils.UserUtils;

import javax.servlet.http.HttpServletRequest;
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
    @Autowired
    private NewsCommentDAO newsCommentDAO;

    @Autowired
    private UserDAO userDAO;

    /**
     * 从请求中获取用户名、评论内容及被评论新闻的 URL ，添加评论。
     *
     * @param request 请求
     * @return 评论成功或失败
     */
    public boolean addComment(HttpServletRequest request) {
        String username = request.getParameter("username");
        // 判断用户名是否符合要求，并判断是否存在
        if (UserUtils.UsernameLengthIsRight(username) &&
                UserUtils.UsernameSymbolIsRight(username) &&
                userDAO.existsByName(username)) {
            NewsComment newsComment = new NewsComment();
            User user = new User();
            user.setName(username);
            newsComment.setUser(user);
            newsComment.setContent(request.getParameter("content"));
            newsComment.setNewsURL(request.getParameter("newsURL"));
            newsComment.setTime(new Timestamp(new Date().getTime()));
            // 判断评论内容长度、新闻 URL 是否符合要求
            if (newsComment.getContent() != null &&
                    NewsCommentUtils.ContentIsLong(newsComment.getContent()) &&
                    NewsCommentUtils.IsURL(newsComment.getNewsURL())) {
                newsCommentDAO.save(newsComment);
                NewsLogger.info(user.getName() + "评论成功。");
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
     */
    public List<NewsComment> getComments(String newsURL) {
        if (NewsCommentUtils.IsURL(newsURL)) {
            return newsCommentDAO.findByNewsURL(newsURL);
        }
        return null;
    }

}
