package ac.daffodil.repository;

import ac.daffodil.model.Exam;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Bithi on 3/24/2018.
 */
public interface ExamRepository extends JpaRepository<Exam, Long> {
}
