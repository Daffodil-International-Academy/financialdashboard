package ac.daffodil.repository;

import ac.daffodil.model.Department;
import ac.daffodil.model.Financial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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

    @Query(value = "select last_update_date, SUM(financial_id) as financial_id, SUM(organization_id) as organization_id, SUM(collection_cash) as collection_cash, SUM(collection_bank) as collection_bank, SUM(total_payment_cash) as total_payment_cash, SUM(total_payment_bank) as total_payment_bank, SUM(balance_bank) as balance_bank, SUM(balance_cash) as balance_cash, SUM(accounts_receivable) as accounts_receivable, SUM(accounts_payable) as accounts_payable, SUM(ondate_admited_students) as ondate_admited_students, SUM(new_students) as new_students from financial group by last_update_date", nativeQuery = true)
    List<Financial> sumAllFieldByDate();

}
