package com.example.serverinstagram.configuration.security.user;

import com.example.serverinstagram.domain.user.model.User;
import com.example.serverinstagram.domain.user.repository.UserRepository;
import com.example.serverinstagram.domain.user.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {


    private final Logger logger = LoggerFactory.getLogger(UserDetailServiceImpl.class);


    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            logger.error("User not found with username");
            throw new UsernameNotFoundException("User not found with username");
        } else {
            logger.info("User found with username, {} ", username);
        }

        Collection<GrantedAuthority> authorities = new ArrayList<>();
        user.get().getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new org.springframework.security.core.userdetails.User(user.get().getUsername(), user.get().getPassword(), authorities);

    }
}
