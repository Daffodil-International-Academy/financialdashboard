package ac.daffodil.repository;

import ac.daffodil.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Muiduzzaman Lipu on 19-May-18.
 */
@Repository("departmentRepository")
public interface DepartmentRepository extends JpaRepository<Department, Long>{
}
