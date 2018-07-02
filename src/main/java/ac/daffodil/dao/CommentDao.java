package ac.daffodil.dao;

import ac.daffodil.model.Comments;
import ac.daffodil.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * Created by Bithi on 4/2/2018.
 */
@Repository
@Transactional
public class CommentDao implements GenericInterface<Comments> {
    @Autowired
    CommentRepository commentRepository;

    @Override
    public Comments save(Comments comments) {
       commentRepository.save(comments);
        return comments;
    }

    @Override
    public Comments update(Comments comments) {
        commentRepository.save(comments);
        return comments;
    }

    @Override
    public boolean delete(Comments comments) {
        commentRepository.delete(comments);
        return true;
    }

    @Override
    public List<Comments> getAll() {
        return commentRepository.findAll();

    }

    @Override
    public Optional<Comments> find(Long id) {
        return commentRepository.findById(id);
    }
}
