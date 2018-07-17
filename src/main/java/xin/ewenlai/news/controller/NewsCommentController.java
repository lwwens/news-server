package xin.ewenlai.news.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xin.ewenlai.news.pojo.NewsComment;
import xin.ewenlai.news.service.NewsCommentService;
import xin.ewenlai.news.utils.Code;
import xin.ewenlai.news.utils.NewsLogger;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
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
    private final NewsCommentService newsCommentService;

    @Autowired
    public NewsCommentController(NewsCommentService newsCommentService) {
        this.newsCommentService = newsCommentService;
    }

    /**
     * 添加评论。
     *
     * @param request 请求
     * @return 返回的 JSON 数据
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    public JSONObject addComment(HttpServletRequest request) throws UnsupportedEncodingException {
        JSONObject jsonObject = new JSONObject();
        String username = request.getParameter("username");
        String newsURL = request.getParameter("newsURL");
        if (!newsCommentService.existsByUsernameAndNewsURL(username, newsURL) &&
                newsCommentService.addComment(request)) {
            jsonObject.put("code", Code.SUCCESS.getValue());
            jsonObject.put("message", username + "评论成功。");
            NewsLogger.info(username + "评论成功。");
        } else {
            jsonObject.put("code", Code.FAIL.getValue());
            jsonObject.put("message", username + "评论失败。");
            NewsLogger.info(username + "评论失败。");
        }
        return jsonObject;
    }

    /**
     * 获取新闻的评论。
     *
     * @param newsURL 新闻链接
     * @return 返回 JSON 数据
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public JSONObject getCommentsByNewsURL(@RequestParam String newsURL) {
        JSONObject jsonObject = new JSONObject();
        List<NewsComment> newsComments = newsCommentService.getCommentsByNewsURL(newsURL);
        if (newsComments != null) {
            jsonObject.put("code", Code.SUCCESS.getValue());
            jsonObject.put("message", "获取" + newsURL + "评论成功。");
            jsonObject.put("data", newsComments);
            NewsLogger.info("获取" + newsURL + "评论成功。");
        } else {
            jsonObject.put("code", Code.FAIL.getValue());
            jsonObject.put("message", "获取" + newsURL + "评论失败。");
            jsonObject.put("data", null);
            NewsLogger.info("获取" + newsURL + "评论失败。");
        }
        return jsonObject;
    }

    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    public JSONObject getCommentsByUsername(@PathVariable(value = "username") String username) {
        JSONObject jsonObject = new JSONObject();
        newsCommentService.getCommentsByUsername(username);
        jsonObject.put("code", Code.SUCCESS.getValue());
        jsonObject.put("message", "获取用户" + username + "的所有评论。");
        jsonObject.put("data", newsCommentService.getCommentsByUsername(username));
        NewsLogger.info("获取用户" + username + "的所有评论。");
        return jsonObject;
    }

    /**
     * 删除指定 ID 的评论。
     *
     * @param id 评论 ID
     * @return 返回请求结果
     * @date 18-7-13
     * @time 下午5:33
     * @author lwwen
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public JSONObject deleteCommentById(@PathVariable long id) {
        JSONObject jsonObject = new JSONObject();
        if (newsCommentService.deleteCommentById(id)) {
            jsonObject.put("code", Code.SUCCESS.getValue());
            jsonObject.put("message", "删除评论" + id + "成功。");
            jsonObject.put("data", null);
            NewsLogger.info("删除评论" + id + "成功。");
        } else {
            jsonObject.put("code", Code.FAIL.getValue());
            jsonObject.put("message", "评论" + id + "不存在，删除失败。");
            jsonObject.put("data", null);
            NewsLogger.info("评论" + id + "不存在，删除失败。");
        }
        return jsonObject;
    }


}
