package ac.daffodil.dao;

import ac.daffodil.model.File;
import ac.daffodil.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * Created by Bithi on 3/25/2018.
 */
@Repository
@Transactional
public class FileDao implements GenericInterface<File>{

    @Autowired
    FileRepository fileRepository;

    @Override
    public File save(File file) {
        fileRepository.save(file);
        return file;
    }

    @Override
    public File update(File file) {
        fileRepository.save(file);
        return file;
    }

    @Override
    public boolean delete(File file) {
        fileRepository.delete(file);
        return true;
    }

    @Override
    public List<File> getAll() {
        return fileRepository.findAll();
    }

    @Override
    public Optional<File> find(Long id) {
        return fileRepository.findById(id);
    }
}
