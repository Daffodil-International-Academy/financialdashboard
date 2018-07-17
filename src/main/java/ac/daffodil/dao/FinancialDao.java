package ac.daffodil.dao;

import ac.daffodil.model.Financial;
import ac.daffodil.repository.FinancialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Created by Muiduzzaman Lipu on 19-May-18.
 */
@Repository
@Transactional
public class FinancialDao implements GenericInterface<Financial>{

    @Qualifier("financialRepository")
    @Autowired
    private FinancialRepository financialRepository;

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Financial save(Financial financial) {
        financialRepository.save(financial);
        return financial;
    }

    @Override
    public Financial update(Financial financial) {
        financialRepository.save(financial);
        return financial;
    }

    @Override
    public boolean delete(Financial financial) {
        financialRepository.delete(financial);
        return true;
    }

    @Override
    public List<Financial> getAll() {
        return financialRepository.findAll();
    }

    @Override
    public Optional<Financial> find(Long id) {
        return financialRepository.findById(id);
    }

    public List<Financial> findByOrganizationId(Long id) {
        return financialRepository.findByOrganizationId(id);
    }

    public List<Financial> findByLastUpdateDate(Date date) {
        return financialRepository.findByLastUpdateDate(date);
    }

    public Optional<Financial> findByOrganizationIdAndLastUpdateDate(Long id, Date date) {
        return financialRepository.findByOrganizationIdAndLastUpdateDate(id, date);
    }

    public List<Financial> sumAllFieldByDate() {
        return financialRepository.sumAllFieldByDate();

    }
}
