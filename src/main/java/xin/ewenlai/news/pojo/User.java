package xin.ewenlai.news.pojo;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * description : Users 表的实体类。
 *
 * @author lwwen
 * date : 2018-07-11 17:31
 * @version 0.0.1
 */
@Entity
@Table(name = "users")
public class User {
    public static final String defaultProfilePiecture = "/static/upload/monkey.jpg";

    @Id
    @Size(min = 6, max = 20)
    private String name;

    @NotNull
    @Size(min = 6, max = 30)
    private String nickname;

    @NotNull
    @Size(min = 6, max = 20)
    private String password;

    @Column(length = 1)
    @NotNull
    private String sex;

    @NotNull
    @Size(max = 200)
    private String profilePicture;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    @Override
    public String toString() {
        return "User: [name :" + name +
                ", nickname: " + nickname +
                ", password: " + password +
                ", sex: " + sex +
                ", profilePicture: " + profilePicture + " ] ";
    }
}