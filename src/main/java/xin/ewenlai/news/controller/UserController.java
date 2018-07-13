package xin.ewenlai.news.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xin.ewenlai.news.pojo.User;
import xin.ewenlai.news.service.UserService;
import xin.ewenlai.news.utils.Code;
import xin.ewenlai.news.utils.NewsLogger;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    public Object login(@RequestParam(value = "username") String username,
                        @RequestParam(value = "password") String password) {
        JSONObject jsonObject = new JSONObject();
        List<Integer> codes = new ArrayList<>();
        // 判断 username 长度是否合理
        if (username.length() <= 6 || username.length() >= 20) {
            codes.add(Code.UsernameLengthIsWrong.getValue());
        }

        // 判断 username 符号是否正确
        String usernameRegEx = "[a-zA-Z]{1}[a-zA-Z0-9_]";   // 跟变量名规则一样
        Pattern usernamePattern = Pattern.compile(usernameRegEx);
        Matcher usernameMatcher = usernamePattern.matcher(username);
        if (!usernameMatcher.find()) {
            codes.add(Code.UsernameSymbolIsWrong.getValue());
        }

        // 判断 password 长度是否合理
        if (password.length() <= 6 || password.length() >= 20) {
            codes.add(Code.PasswordLengthIsWrong.getValue());
        }

        // 判断 password 符号是否合理
        int flag = 0;
        flag += password.matches(".*\\d+.*") ? 1 : 0;        // 判断是否含有数字
        flag += password.matches(".*[a-zA-Z]+.*") ? 1 : 0;   // 判断是否含有字母
        flag += password.matches(".*[~!@#$%^&*()_+|<>,.?/:;'\\[\\]{}\"]+.*") ? 1 : 0;   // 判断是否含有特殊符号
        if (flag <= 1) {
            codes.add(Code.PasswordSymbolIsWrong.getValue());
        }
        // 判断 username 或 password 是否不符合规则
        if (codes.size() > 0) {
            jsonObject.put("codes", codes);
            jsonObject.put("message", "用户名或密码不符合规则");
            return jsonObject;
        }

        if (userService.login(username, password)) {
            codes.add(Code.Success.getValue());
            jsonObject.put("codes", codes);
            jsonObject.put("message", "用户" + username + "登录成功。");
            NewsLogger.info("用户" + username + "登录成功。");
        } else {
            codes.add(Code.UserIsNotExists.getValue());
            jsonObject.put("codes", codes);
            jsonObject.put("message", "用户" + username + "不存在。");
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
    public User getUser(@PathVariable String username) {
        return userService.getUser(username);
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
            jsonObject.put("code", Code.Success.getValue());
            jsonObject.put("message", "新用户" + username + "注册成功。");
        } else {
            jsonObject.put("code", Code.UserIsExists.getValue());
            jsonObject.put("message", "用户" + username + "已存在，注册失败。");
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
        if (userService.modifyUser(username, request)) {
            jsonObject.put("code", Code.Success.getValue());
            jsonObject.put("message", username + "修改信息成功。");
        } else {
            jsonObject.put("code", Code.UserIsNotExists.getValue());
            jsonObject.put("message", "用户" + username + "不存在。");
        }
        return jsonObject;
    }

}
