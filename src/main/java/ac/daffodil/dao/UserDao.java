package ac.daffodil.dao;

import ac.daffodil.model.User;
import ac.daffodil.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created by Muiduzzaman Lipu on 3/24/2018.
 */
@Service
public class UserDao implements GenericInterface<User>{

    @Qualifier("userRepository")
    @Autowired
    private UserRepository userRepository;

    @Override
    public User save(User user) {
        userRepository.save(user);
        return user;
    }

    @Override
    public User update(User user) {
        userRepository.save(user);
        return user;
    }

    @Override
    public boolean delete(User user) {
        userRepository.delete(user);
        return true;
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> find(Long id) {
        return userRepository.findById(id);
    }

    public Optional<User> findByUsername(String userName) {
        return userRepository.findByFirstName(userName);
    }

    public User findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public void updateUserPassword(String password,Long id){
        userRepository.updatePassword(password,id);
    }
}
