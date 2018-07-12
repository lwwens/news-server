package xin.ewenlai.news.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import xin.ewenlai.news.service.NewsCommentService;
import xin.ewenlai.news.utils.Code;

import javax.servlet.http.HttpServletRequest;


/**
 * description : 新闻评论控制器，提供评论的 CRUP 功能。
 *
 * @author lwwen
 * date : 2018-07-12 17:15
 * @version 0.0.1
 */
@Controller
@RequestMapping(value = "/comment")
public class NewsCommentController {
    @Autowired
    private NewsCommentService newsCommentService;

    /**
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public JSONObject addComment(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        if (newsCommentService.addComment(request)) {
            jsonObject.put("code", Code.SUCCESS);
            jsonObject.put("message", request.getParameter("username") + "评论成功。");
        } else {
            jsonObject.put("code", Code.FAIL);
            jsonObject.put("message", request.getParameter("username") + "评论失败。");
        }
        return jsonObject;
    }


}
