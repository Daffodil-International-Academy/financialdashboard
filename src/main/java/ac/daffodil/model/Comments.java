package ac.daffodil.model;

/**
 * Created by Bithy on 4/2/2018.
 */

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "comments")
public class Comments {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long comment_id;


    @Column(nullable = false)
    private String user_email;

    @NotEmpty
    private String comment_text;

    @CreationTimestamp
    private LocalDateTime date_time;

    @UpdateTimestamp
    private LocalDateTime updated_date_time;

    @ManyToOne
    private File file;

    @OneToMany(mappedBy = "comments")
    private List<ChildComments> childComments=new ArrayList<>();





    public Comments() {
    }


    public Comments(String user_email, String comment_text, LocalDateTime date_time, LocalDateTime updated_date_time, File file) {
        this.user_email = user_email;
        this.comment_text = comment_text;
        this.date_time = date_time;
        this.updated_date_time = updated_date_time;
        this.file = file;
    }


    public Long getComment_id() {
        return comment_id;
    }

    public void setComment_id(Long comment_id) {
        this.comment_id = comment_id;
    }

    public String getComment_text() {
        return comment_text;
    }

    public void setComment_text(String comment_text) {
        this.comment_text = comment_text;
    }

    public LocalDateTime getDate_time() {
        return date_time;
    }

    public LocalDateTime getUpdated_date_time() {
        return updated_date_time;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }


    public void setDate_time(LocalDateTime date_time) {
        this.date_time = date_time;
    }

    public List<ChildComments> getChildComments() {
        return childComments;
    }

    public void setChildComments(List<ChildComments> childComments) {
        this.childComments = childComments;
    }

    @Override
    public String toString() {
        return "Comments{" +
                "comment_id=" + comment_id +
                ", user_email='" + user_email + '\'' +
                ", comment_text='" + comment_text + '\'' +
                ", date_time=" + date_time +
                ", updated_date_time=" + updated_date_time +
                ", file=" + file +
                ", childComments=" + childComments +
                '}';
    }
}
