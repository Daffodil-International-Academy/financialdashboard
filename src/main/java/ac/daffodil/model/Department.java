package ac.daffodil.model;

import javax.persistence.*;

/**
 * Created by Muiduzzaman Lipu on 19-May-18.
 */
@Entity
@Table(name = "department")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="department_id")
    private Long departmentId;

    @Column(name="university_id")
    private Long universityId;

    @Column(name="department_name")
    private String departmentName;

    @Column(name="course_duration")
    private String courseDuration;

    @Column(name="amount")
    private Float amount;

    public Department() {
    }

    public Department(Long universityId, String departmentName, String courseDuration, Float amount) {
        this.universityId = universityId;
        this.departmentName = departmentName;
        this.courseDuration = courseDuration;
        this.amount = amount;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Long getUniversityId() {
        return universityId;
    }

    public void setUniversityId(Long universityId) {
        this.universityId = universityId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getCourseDuration() {
        return courseDuration;
    }

    public void setCourseDuration(String courseDuration) {
        this.courseDuration = courseDuration;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Department{" +
                "departmentId=" + departmentId +
                ", universityId=" + universityId +
                ", departmentName='" + departmentName + '\'' +
                ", courseDuration='" + courseDuration + '\'' +
                ", amount=" + amount +
                '}';
    }
}
