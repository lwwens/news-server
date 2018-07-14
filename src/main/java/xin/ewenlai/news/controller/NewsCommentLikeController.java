package xin.ewenlai.news.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import xin.ewenlai.news.service.NewsCommentLikeService;
import xin.ewenlai.news.utils.Code;
import xin.ewenlai.news.utils.NewsLogger;

import javax.servlet.http.HttpServletRequest;

/**
 * @author lwwen
 * @version 0.0.0
 * @create 2018-07-14 10:04
 */
@RestController
@RequestMapping(value = "/like")
public class NewsCommentLikeController {
    private final NewsCommentLikeService newsCommentLikeService;

    @Autowired
    public NewsCommentLikeController(NewsCommentLikeService newsCommentLikeService) {
        this.newsCommentLikeService = newsCommentLikeService;
    }

    /**
     * 给某个评论添加点赞。
     *
     * @param request 请求
     * @return 点赞结果
     * @date 18-7-14
     * @time 上午10:11
     * @author lwwen
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    public JSONObject addLike(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        String username = request.getParameter("username");
        long commentId = Long.parseLong(request.getParameter("commentId"));
        // 判断用户是否已经点赞某评论
        if (!newsCommentLikeService.existsByUsernameAndCommentId(username, commentId)) {
            if (newsCommentLikeService.addLike(request)) {
                jsonObject.put("code", Code.SUCCESS.getValue());
                jsonObject.put("message", "用户" + username + "点赞id为" + commentId + "的评论成功。");
                NewsLogger.info("用户" + username + "点赞id为" + commentId + "的评论成功。");
            } else {
                jsonObject.put("code", Code.FAIL.getValue());
                jsonObject.put("message", "用户" + username + "或id为" + commentId + "的评论不存在，点赞失败。");
                NewsLogger.info("用户" + username + "或id为" + commentId + "的评论不存在，点赞失败。");
            }
        } else {
            jsonObject.put("code", Code.FAIL.getValue());
            jsonObject.put("message", "用户" + username + "已经点赞id为" + commentId + "评论，不允许重复点赞。");
            NewsLogger.info("用户" + username + "已经点赞id为" + commentId + "评论，不允许重复点赞。");
        }
        return jsonObject;
    }
}
