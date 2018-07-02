package ac.daffodil.model;

import javax.persistence.*;

/**
 * Created by Muiduzzaman Lipu on 19-May-18.
 */
@Entity
@Table(name = "university")
public class University {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="university_id")
    private Long universityId;

    @Column(name="university_name")
    private String universityName;

    @Lob
    @Column(name="pic")
    private byte[] pic;

    @Column(name="status")
    private String status;

    @Column(name="location")
    private String location;

    @Column(name="total_student")
    private Long totalStudent;

    @Column(name="about")
    private String about;

    public University() {
    }

    public Long getUniversityId() {
        return universityId;
    }

    public void setUniversityId(Long universityId) {
        this.universityId = universityId;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    public byte[] getPic() {
        return pic;
    }

    public void setPic(byte[] pic) {
        this.pic = pic;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Long getTotalStudent() {
        return totalStudent;
    }

    public void setTotalStudent(Long totalStudent) {
        this.totalStudent = totalStudent;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }
}
