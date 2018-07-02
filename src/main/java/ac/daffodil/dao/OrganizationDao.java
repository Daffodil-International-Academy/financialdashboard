package ac.daffodil.dao;

import ac.daffodil.model.Organization;
import ac.daffodil.repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * Created by Muiduzzaman Lipu on 19-May-18.
 */
@Repository
@Transactional
public class OrganizationDao implements GenericInterface<Organization>{

    @Qualifier("organizationRepository")
    @Autowired
    private OrganizationRepository organizationRepository;

    @Override
    public Organization save(Organization organization) {
        organizationRepository.save(organization);
        return organization;
    }

    @Override
    public Organization update(Organization organization) {
        organizationRepository.save(organization);
        return organization;
    }

    @Override
    public boolean delete(Organization organization) {
        organizationRepository.delete(organization);
        return true;
    }

    @Override
    public List<Organization> getAll() {
        return organizationRepository.findAll();
    }

    @Override
    public Optional<Organization> find(Long id) {
        return organizationRepository.findById(id);
    }
}
