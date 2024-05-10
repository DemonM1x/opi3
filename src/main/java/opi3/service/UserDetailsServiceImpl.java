package opi3.service;

import opi3.model.NewUser;
import opi3.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository user;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final NewUser foundUser = user.findByUsername(username);
        if (foundUser == null) {
            throw new UsernameNotFoundException("Логин не найден");

        }
        return foundUser;

    }
}
