package xin.ewenlai.news.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xin.ewenlai.news.dao.UserDAO;
import xin.ewenlai.news.pojo.User;
import xin.ewenlai.news.utils.NewsLogger;
import xin.ewenlai.news.utils.UserUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * description : 用户管理服务，提供用户的登录、注册、修改信息等服务。
 *
 * @author lwwen
 * date : 2018-07-12 13:23
 * @version 0.0.3
 */
@Service
public class UserService {
    @Autowired
    private UserDAO userDAO;

    /**
     * 验证是否登录成功。
     *
     * @param username 用户名
     * @param password 密码
     * @return 登录成功或失败
     */
    public boolean login(String username, String password) {
        return UserUtils.UsernameLengthIsRight(username) &&
                UserUtils.UsernameSymbolIsRight(username) &&
                UserUtils.PasswordLengthIsRight(password) &&
                UserUtils.PasswordSymbolIsRight(password) &&
                null != userDAO.findByNameAndPassword(username, password);
    }

    /**
     * 获取用户，密码除外。
     *
     * @param username 用户名
     * @return 用户对象。
     */
    public User getUser(String username) {
        if (username != null &&
                UserUtils.UsernameLengthIsRight(username) &&
                UserUtils.UsernameSymbolIsRight(username)) {
            User user = userDAO.findByName(username);
            user.setPassword("********");
            return user;
        }
        return null;
    }

    /**
     * 从请求中获取用户信息，添加用户记录，用户头像是默认图片。
     *
     * @param request 请求
     * @return 注册成功或失败
     */
    public boolean register(HttpServletRequest request) {
        String username = request.getParameter("username");
        // 判断用户名是否符合要求，且该用户是否不存在
        if (UserUtils.UsernameLengthIsRight(username) &&
                UserUtils.UsernameSymbolIsRight(username) &&
                !userDAO.existsByName(username)) {
            User user = new User();
            user.setName(username);
            user.setPassword(request.getParameter("password"));
            user.setNickname(request.getParameter("nickname"));
            user.setSex(request.getParameter("sex"));

            // 判断其他字段是否符合要求
            if (UserUtils.PasswordLengthIsRight(user.getPassword()) &&
                    UserUtils.PasswordSymbolIsRight(user.getPassword()) &&
                    UserUtils.NicknameLengthIsRight(user.getNickname()) &&
                    UserUtils.SexIsRight(user.getSex())) {
                user.setProfilePicture(User.defaultProfilePiecture);
                userDAO.save(user);
                NewsLogger.info(user.toString() + "注册成功");
                return true;
            }
        }
        NewsLogger.info("用户" + username + "已存在，注册失败。");
        return false;
    }

    /**
     * 从请求中获取用户信息，修改用户信息，但不修改用户头像。
     *
     * @param request 请求
     * @return 修改成功或失败。
     */
    public User modifyUser(String username, HttpServletRequest request) {
        // 判断用户名是否符合要求
        if (UserUtils.UsernameLengthIsRight(username) &&
                UserUtils.UsernameSymbolIsRight(username)) {
            // 判断旧密码是否符合规则
            String oldPassword = request.getParameter("oldPassword");
            if (oldPassword != null &&
                    UserUtils.PasswordLengthIsRight(oldPassword) &&
                    UserUtils.PasswordSymbolIsRight(oldPassword)) {
                User user = userDAO.findByNameAndPassword(username, oldPassword);
                // 用户不为空
                if (user != null) {
                    user.setPassword(request.getParameter("newPassword"));
                    user.setNickname(request.getParameter("nickname"));
                    user.setSex(request.getParameter("sex"));

                    // 判断新字段是否符合要求
                    if (user.getPassword() != null &&
                            UserUtils.PasswordLengthIsRight(user.getPassword()) &&
                            UserUtils.PasswordSymbolIsRight(user.getPassword()) &&
                            UserUtils.NicknameLengthIsRight(user.getNickname()) &&
                            UserUtils.SexIsRight(user.getSex())) {
                        userDAO.save(user);
                        NewsLogger.info(user.toString() + "修改信息成功");
                        return user;
                    }
                    NewsLogger.info("用户" + username + "信息不符合要求，修改失败。");
                    return null;
                }
            }
        }
        NewsLogger.info("用户" + username + "不存在。");
        return null;
    }
}
