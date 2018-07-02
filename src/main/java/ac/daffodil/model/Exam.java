package ac.daffodil.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

/**
 * Created by Bithy on 3/21/2018.
 */
@Entity
@Table(name="exam")
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "exam_id")
    private Long exam_id;


    @Column(name="exam_type")
    @NotEmpty(message = "*please select an examtype")
    private String exam_type;

    @Column(name="exam_name")
    @NotEmpty(message = "*please select exam name")
    private String exam_name;

    public Exam() {
    }

    public Exam(@NotEmpty(message = "*please select an examtype") String exam_type, @NotEmpty(message = "*please select exam name") String exam_name) {
        this.exam_type = exam_type;
        this.exam_name = exam_name;
    }

    public Long getExam_id() {
        return exam_id;
    }

    public void setExam_id(Long exam_id) {
        this.exam_id = exam_id;
    }

    public String getExam_type() {
        return exam_type;
    }

    public void setExam_type(String exam_type) {
        this.exam_type = exam_type;
    }

    public String getExam_name() {
        return exam_name;
    }

    public void setExam_name(String exam_name) {
        this.exam_name = exam_name;
    }

    @Override
    public String toString() {
        return "Exam{" +
                "exam_id=" + exam_id +
                ", exam_type='" + exam_type + '\'' +
                ", exam_name='" + exam_name + '\'' +
                '}';
    }
}
