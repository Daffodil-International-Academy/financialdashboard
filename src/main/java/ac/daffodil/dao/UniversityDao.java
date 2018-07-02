package ac.daffodil.dao;

import ac.daffodil.model.University;
import ac.daffodil.repository.UniversityRepository;
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
public class UniversityDao implements GenericInterface<University>{

    @Qualifier("universityRepository")
    @Autowired
    private UniversityRepository universityRepository;

    @Override
    public University save(University university) {
        universityRepository.save(university);
        return university;
    }

    @Override
    public University update(University university) {
        universityRepository.save(university);
        return university;
    }

    @Override
    public boolean delete(University university) {
        universityRepository.delete(university);
        return true;
    }

    @Override
    public List<University> getAll() {
        return universityRepository.findAll();
    }

    @Override
    public Optional<University> find(Long id) {
        return universityRepository.findById(id);
    }
}
