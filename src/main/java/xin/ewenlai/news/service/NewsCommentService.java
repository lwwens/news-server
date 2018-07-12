package xin.ewenlai.news.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xin.ewenlai.news.dao.NewsCommentDAO;
import xin.ewenlai.news.pojo.NewsComment;
import xin.ewenlai.news.pojo.User;
import xin.ewenlai.news.utils.NewsLogger;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    /**
     * 从请求中获取用户名、评论内容及被评论新闻的 URL ，添加评论。
     *
     * @param request 请求
     * @return 评论成功或失败
     */
    public boolean addComment(HttpServletRequest request) {
        NewsComment newsComment = new NewsComment();
        User user = new User();
        user.setName(request.getParameter("username"));
        newsComment.setUser(user);
        newsComment.setContent(request.getParameter("content"));
        newsComment.setNewsURL(request.getParameter("newsURL"));
        newsComment.setTime(new Timestamp(new Date().getTime()));
        newsCommentDAO.save(newsComment);
        NewsLogger.info(user.getName() + "评论成功。");
        return true;
    }

}
