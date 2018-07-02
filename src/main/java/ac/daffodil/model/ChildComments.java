package ac.daffodil.model;

import com.sun.javafx.beans.IDProperty;

import javax.persistence.*;

/**
 * Created by Bithi on 4/4/2018.
 */
@Entity
public class ChildComments {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ccomments_id;


    private String sub_comments;

    private String user_name;

    @ManyToOne
    private Comments comments;

    public ChildComments() {
    }

    public ChildComments(String sub_comments) {
        this.sub_comments = sub_comments;

    }

    public ChildComments(String sub_comments, String user_name) {
        this.sub_comments = sub_comments;
        this.user_name = user_name;
    }

    public Long getCcomments_id() {
        return ccomments_id;
    }

    public void setCcomments_id(Long ccomments_id) {
        this.ccomments_id = ccomments_id;
    }

    public String getSub_comments() {
        return sub_comments;
    }

    public void setSub_comments(String sub_comments) {
        this.sub_comments = sub_comments;
    }

    public Comments getComments() {
        return comments;
    }

    public void setComments(Comments comments) {
        this.comments = comments;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    @Override
    public String toString() {
        return "ChildComments{" +
                "ccomments_id=" + ccomments_id +
                ", sub_comments='" + sub_comments + '\'' +
                ", user_name='" + user_name + '\'' +
                ", comments=" + comments +
                '}';
    }
}
