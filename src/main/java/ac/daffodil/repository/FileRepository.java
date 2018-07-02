package ac.daffodil.repository;

import ac.daffodil.model.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Bithi on 3/25/2018.
 */
public interface FileRepository extends JpaRepository<File,Long> {

}
