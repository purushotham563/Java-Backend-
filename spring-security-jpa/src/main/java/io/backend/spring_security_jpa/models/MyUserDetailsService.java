package io.backend.spring_security_jpa.models;

import io.backend.spring_security_jpa.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String userName)
            throws UsernameNotFoundException {
        User user = userRepository.findByUserName(userName)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found: " + userName));


        String []rolesArray=
                Arrays.stream(user.getRoles().split(",")).
                        map(String::trim).toArray(String[]::new);
        return org.springframework.
                security.core.userdetails.User.builder().
                username(user.getUserName())
                .password(user.getPassword()).roles(rolesArray).disabled(!user.isActive()).
                build();
    }
}
