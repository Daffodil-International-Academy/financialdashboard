package ac.daffodil.dao;

import ac.daffodil.model.Role;
import ac.daffodil.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created by Lipu on 21-Mar-18.
 */
@Service
public class RoleDao implements GenericInterface<Role>{

    @Qualifier("roleRepository")
    @Autowired
    private RoleRepository roleRepository;


    @Override
    public Role save(Role role) {
        roleRepository.save(role);
        return role;
    }

    @Override
    public Role update(Role role) {
        roleRepository.save(role);
        return role;
    }

    @Override
    public boolean delete(Role role) {
        roleRepository.delete(role);
        return true;
    }

    @Override
    public List<Role> getAll() {
        return roleRepository.findAll();
    }

    @Override
    public Optional<Role> find(Long id) {
        return roleRepository.findById(id);
    }


}
