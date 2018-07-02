package ac.daffodil.repository;

import ac.daffodil.model.University;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Muiduzzaman Lipu on 19-May-18.
 */
@Repository("universityRepository")
public interface UniversityRepository extends JpaRepository<University, Long>{
}
