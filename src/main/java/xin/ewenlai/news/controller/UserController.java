package xin.ewenlai.news.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xin.ewenlai.news.pojo.User;
import xin.ewenlai.news.service.UserService;
import xin.ewenlai.news.utils.Code;
import xin.ewenlai.news.utils.NewsLogger;

import javax.servlet.http.HttpServletRequest;

/**
 * description : 用户控制器，提供用户的登录、注册、修改信息等功能。
 *
 * @author lwwen
 * date : 2018-07-11 19:41
 * @version 0.0.1
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    UserService userService;

    /**
     * 判断用户是否登录。
     *
     * @param username 用户名
     * @param password 密码
     * @return 登录结果
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Object login(@RequestParam(value = "username") String username,
                        @RequestParam(value = "password") String password) {
        JSONObject jsonObject = new JSONObject();
        if (userService.login(username, password)) {
            jsonObject.put("code", Code.SUCCESS);
            jsonObject.put("message", "登录成功。");

            NewsLogger.info(username + "登录成功。");
        } else {
            jsonObject.put("code", Code.FAIL);
            jsonObject.put("message", "帐号或密码不存在。");

            NewsLogger.warning(username + "登录失败。");
        }
        return jsonObject;
    }

    /**
     * 获取用户。
     *
     * @param username 用户名
     * @return 用户对象
     */
    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    public User getUser(@PathVariable String username) {
        return userService.getUser(username);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public JSONObject register(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        String username = request.getParameter("username");
        if (userService.register(request)) {
            jsonObject.put("code", Code.SUCCESS);
            jsonObject.put("message", "新用户" + username + "注册成功。");
        } else {
            jsonObject.put("code", Code.FAIL);
            jsonObject.put("message", "旧用户" + username + "已存在，注册失败。");
        }
        return jsonObject;
    }
}
