package ac.daffodil.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bithy on 3/21/2018.
 */
@Entity
@Table(name = "files")
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "file_id")
    private Long file_id;

    @Column(name = "exam_id")
    private Long exam_id;

    @Column(name = "file_type")
    private String file_type;

    @Column(name = "file_name")
    private String file_name;

    @Column(name = "subject_name")
    private String subject_name;

    @Column(name="year")
    private String year;


    @OneToMany(mappedBy = "file")
    private List<Comments> comments=new ArrayList<>();

    public File() {
    }

    public File(Long exam_id, String file_type, String file_name, String subject_name, @NotEmpty(message = "*Please provide year") String year) {
        this.exam_id = exam_id;
        this.file_type = file_type;
        this.file_name = file_name;
        this.subject_name = subject_name;
        this.year = year;
    }

    public Long getFile_id() {
        return file_id;
    }

    public void setFile_id(Long file_id) {
        this.file_id = file_id;
    }

    public Long getExam_id() {
        return exam_id;
    }

    public void setExam_id(Long exam_id) {
        this.exam_id = exam_id;
    }

    public String getFile_type() {
        return file_type;
    }

    public void setFile_type(String file_type) {
        this.file_type = file_type;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public String getSubject_name() {
        return subject_name;
    }

    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public List<Comments> getComments() {
        return comments;
    }

    public void addComments(Comments comments) {
        this.comments.add(comments);
    }

    public void removeComments(Comments comments) {
        this.comments.remove(comments);
    }

    public void setComments(List<Comments> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "File{" +
                "file_id=" + file_id +
                ", exam_id=" + exam_id +
                ", file_type='" + file_type + '\'' +
                ", file_name='" + file_name + '\'' +
                ", subject_name='" + subject_name + '\'' +
                '}';
    }
}
