package ac.daffodil.dao;

import ac.daffodil.model.Department;
import ac.daffodil.repository.DepartmentRepository;
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
public class DepartmentDao implements GenericInterface<Department>{

    @Qualifier("departmentRepository")
    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public Department save(Department department) {
        departmentRepository.save(department);
        return department;
    }

    @Override
    public Department update(Department department) {
        departmentRepository.save(department);
        return department;
    }

    @Override
    public boolean delete(Department department) {
        departmentRepository.delete(department);
        return true;
    }

    @Override
    public List<Department> getAll() {
        return departmentRepository.findAll();
    }

    @Override
    public Optional<Department> find(Long id) {
        return departmentRepository.findById(id);
    }
}
