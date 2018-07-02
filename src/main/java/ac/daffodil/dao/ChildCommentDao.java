package ac.daffodil.dao;

import ac.daffodil.model.ChildComments;
import ac.daffodil.repository.ChildCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Created by Bithi on 4/4/2018.
 */
@Repository
public class ChildCommentDao implements GenericInterface<ChildComments> {

    @Autowired
    ChildCommentRepository childCommentRepository;

    @Override
    public ChildComments save(ChildComments childComments) {
        childCommentRepository.save(childComments);
        return childComments;
    }

    @Override
    public ChildComments update(ChildComments childComments) {
        childCommentRepository.save(childComments);
        return childComments;
    }

    @Override
    public boolean delete(ChildComments childComments) {
        childCommentRepository.delete(childComments);
        return true;
    }

    @Override
    public List<ChildComments> getAll() {
        return childCommentRepository.findAll();
    }

    @Override
    public Optional<ChildComments> find(Long id) {
        return childCommentRepository.findById(id);
    }
}
