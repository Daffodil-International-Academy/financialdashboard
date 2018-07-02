package ac.daffodil.repository;

import ac.daffodil.model.Department;
import ac.daffodil.model.Financial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Created by Muiduzzaman Lipu on 19-May-18.
 */
@Repository("financialRepository")
public interface FinancialRepository extends JpaRepository<Financial, Long>{

    List<Financial> findByOrganizationId(Long id);

    Optional<Financial> findByOrganizationIdAndLastUpdateDate(Long id, Date date);

    List<Financial> findByLastUpdateDate(Date date);

}
