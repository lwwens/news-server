package xin.ewenlai.news.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xin.ewenlai.news.dao.UserDAO;
import xin.ewenlai.news.pojo.User;
import xin.ewenlai.news.utils.Code;

/**
 * description : 用户控制器，提供用户的登录、注册、修改信息等服务。
 *
 * @author lwwen
 * date : 2018-07-11 19:41
 * @version 0.0.1
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserDAO userDAO;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Object login(@RequestParam(value = "username") String username,
                         @RequestParam(value = "password") String password) {
        User user = userDAO.findByName(username);
        JSONObject jsonObject = new JSONObject();
        if (user != null && user.getPassword().equals(password)) {
            jsonObject.put("code", Code.SUCCESS);
            jsonObject.put("message", null);
        } else {
            jsonObject.put("code", Code.FAIL);
            jsonObject.put("message", "帐号或密码不存在");
        }

        return jsonObject;
    }
}
