package xin.ewenlai.news.pojo;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * description : Users 表的实体类。
 *
 * @author lwwen
 * date : 2018-07-11 17:31
 * @version 0.0.0
 */
@Entity
@Table(name = "users")
public class User {
    public static final String defaultProfilePiecture = "/static/image/monkey.jpg";

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

    /**
     * Returns a string representation of the object. In general, the
     * {@code toString} method returns a string that
     * "textually represents" this object. The result should
     * be a concise but informative representation that is easy for a
     * person to read.
     * It is recommended that all subclasses override this method.
     * <p>
     * The {@code toString} method for class {@code Object}
     * returns a string consisting of the name of the class of which the
     * object is an instance, the at-sign character `{@code @}', and
     * the unsigned hexadecimal representation of the hash code of the
     * object. In other words, this method returns a string equal to the
     * value of:
     * <blockquote>
     * <pre>
     * getClass().getName() + '@' + Integer.toHexString(hashCode())
     * </pre></blockquote>
     *
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "User: [name :" + name +
                ", nickname: " + nickname +
                ", password: " + password +
                ", sex: " + sex +
                ", profilePicture: " + profilePicture + " ] ";
    }
}