package ac.daffodil.dao;

import ac.daffodil.model.Exam;
import ac.daffodil.repository.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * Created by Bithy on 3/24/2018.
 */
@Repository
@Transactional
public class ExamDao implements GenericInterface<Exam> {

    @Autowired
    private ExamRepository examRepository;

    @Override
    public Exam save(Exam exam) {
        examRepository.save(exam);
        return exam;
    }

    @Override
    public Exam update(Exam exam) {
        examRepository.save(exam);
        return exam;
    }

    @Override
    public boolean delete(Exam exam) {
        examRepository.delete(exam);
        return true;
    }

    @Override
    public List<Exam> getAll() {
        return examRepository.findAll();
    }

    @Override
    public Optional<Exam> find(Long id) {
        return examRepository.findById(id);
    }


}
