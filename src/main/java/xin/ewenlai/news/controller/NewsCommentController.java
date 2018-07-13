package xin.ewenlai.news.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import xin.ewenlai.news.pojo.NewsComment;
import xin.ewenlai.news.service.NewsCommentService;
import xin.ewenlai.news.utils.Code;
import xin.ewenlai.news.utils.NewsLogger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * description : 新闻评论控制器，提供评论的 CRUP 功能。
 *
 * @author lwwen
 * date : 2018-07-12 17:15
 * @version 0.0.3
 */
@RestController
@RequestMapping(value = "/comment")
public class NewsCommentController {
    @Autowired
    private NewsCommentService newsCommentService;

    /**
     * 添加评论。
     *
     * @param request 请求
     * @return 返回的 JSON 数据
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    public JSONObject addComment(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        if (newsCommentService.addComment(request)) {
            jsonObject.put("code", Code.SUCCESS.getValue());
            jsonObject.put("message", request.getParameter("username") + "评论成功。");
            NewsLogger.info(request.getParameter("username") + "评论成功。");
        } else {
            jsonObject.put("code", Code.FAIL.getValue());
            jsonObject.put("message", request.getParameter("username") + "评论失败。");
            NewsLogger.info(request.getParameter("username") + "评论失败。");
        }
        jsonObject.put("data", null);
        return jsonObject;
    }

    /**
     * 获取新闻的评论。
     *
     * @param newsURL 新闻链接
     * @return 返回 JSON 数据
     */
    @RequestMapping(value = "/{newsURL}", method = RequestMethod.GET)
    public JSONObject getComments(@PathVariable String newsURL) {
        JSONObject jsonObject = new JSONObject();
        List<NewsComment> newsComments = newsCommentService.getComments(newsURL);
        if (newsComments != null) {
            jsonObject.put("code", Code.SUCCESS.getValue());
            jsonObject.put("message", "获取评论成功。");
            jsonObject.put("data", newsComments);
            NewsLogger.info("获取评论成功。");
        } else {
            jsonObject.put("code", Code.FAIL.getValue());
            jsonObject.put("message", "获取评论失败。");
            jsonObject.put("data", null);
            NewsLogger.info("获取评论失败。");
        }
        return jsonObject;
    }


}
