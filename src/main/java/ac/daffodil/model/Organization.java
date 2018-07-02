package ac.daffodil.model;

import javax.persistence.*;

/**
 * Created by Muiduzzaman Lipu on 25-Jun-18.
 */
@Entity
@Table(name = "organization")
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "organization_id")
    private Long organizationId;

    @Column(name = "organization_name")
    private String organizationName;

    public Organization() {
    }

    public Organization(String organizationName) {
        this.organizationName = organizationName;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    @Override
    public String toString() {
        return "Organization{" +
                "organizationId=" + organizationId +
                ", organizationName='" + organizationName + '\'' +
                '}';
    }
}

