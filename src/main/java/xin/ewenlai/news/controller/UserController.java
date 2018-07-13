package xin.ewenlai.news.controller;

import com.alibaba.fastjson.JSONObject;
import org.jetbrains.annotations.NotNull;
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
 * @version 0.0.2
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
    public Object login(@NotNull @RequestParam(value = "username") String username,
                        @NotNull @RequestParam(value = "password") String password) {
        JSONObject jsonObject = new JSONObject();
        if (userService.login(username, password)) {
            jsonObject.put("code", Code.SUCCESS.getValue());
            jsonObject.put("message", "用户" + username + "登录成功。");
            jsonObject.put("data", userService.getUser(username));
            NewsLogger.info("用户" + username + "登录成功。");
        } else {
            jsonObject.put("code", Code.FAIL.getValue());
            jsonObject.put("message", "用户名或密码不存在。");
            jsonObject.put("data", null);
            NewsLogger.warning("用户" + username + "不存在。");
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
    public JSONObject getUser(@PathVariable String username) {
        JSONObject jsonObject = new JSONObject();
        User user = userService.getUser(username);
        if (user != null) {
            jsonObject.put("code", Code.SUCCESS.getValue());
            jsonObject.put("message", "获取用户" + username + "信息成功。");
            jsonObject.put("data", user);
        } else {
            jsonObject.put("code", Code.FAIL.getValue());
            jsonObject.put("message", "获取用户" + username + "信息成功。");
            jsonObject.put("data", null);
        }
        return jsonObject;
    }

    /**
     * 注册用户，从请求中获取用户信息。注册用户时使用默认头像。
     *
     * @param request 请求
     * @return 注册结果
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    public JSONObject register(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        String username = request.getParameter("username");
        if (userService.register(request)) {
            jsonObject.put("code", Code.SUCCESS.getValue());
            jsonObject.put("message", "新用户" + username + "注册成功。");
            jsonObject.put("data", userService.getUser(username));
            NewsLogger.info("新用户" + username + "注册成功。");
        } else {
            jsonObject.put("code", Code.FAIL.getValue());
            jsonObject.put("message", "用户" + username + "已存在，注册失败。");
            jsonObject.put("data", null);
            NewsLogger.info("用户" + username + "已存在，注册失败。");
        }
        return jsonObject;
    }

    /**
     * 从请求中获取用户信息并修改。
     *
     * @param request 请求
     * @return 修改结果
     */
    @RequestMapping(value = "/{username}", method = RequestMethod.PUT)
    public JSONObject modifyUser(@PathVariable String username, HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        User user = userService.modifyUser(username, request);
        if (user != null) {
            jsonObject.put("code", Code.SUCCESS.getValue());
            jsonObject.put("message", username + "修改信息成功。");
            jsonObject.put("data", user);
        } else {
            jsonObject.put("code", Code.FAIL.getValue());
            jsonObject.put("message", "用户" + username + "修改信息失败。");
            jsonObject.put("data", null);
        }
        return jsonObject;
    }

}
