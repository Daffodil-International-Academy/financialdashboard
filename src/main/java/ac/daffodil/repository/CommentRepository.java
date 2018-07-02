package ac.daffodil.repository;

import ac.daffodil.model.Comments;
import ac.daffodil.model.File;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Bithi on 4/2/2018.
 */
public interface CommentRepository extends JpaRepository<Comments,Long>{

}
