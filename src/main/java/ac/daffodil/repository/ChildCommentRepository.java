package ac.daffodil.repository;

import ac.daffodil.model.ChildComments;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Bithi on 4/4/2018.
 */
public interface ChildCommentRepository extends JpaRepository<ChildComments,Long> {
}
