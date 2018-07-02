package ac.daffodil.service;


import ac.daffodil.model.CustomUsersDetails;
import ac.daffodil.model.User;
import ac.daffodil.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by Uzzal on 4/2/2018.
 */
@Service
public class CustomUsersDetailsService implements UserDetailsService {



    @Autowired
    @Qualifier("userRepository")
     private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> optionalUsers = userRepository.findByEmail(email);

        optionalUsers
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));
        return optionalUsers
                .map(CustomUsersDetails::new).get();
    }
}
