package ac.daffodil.repository;

import ac.daffodil.model.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Muiduzzaman Lipu on 19-May-18.
 */
@Repository("organizationRepository")
public interface OrganizationRepository extends JpaRepository<Organization, Long>{
}
