package com.raybyte.jstore.auth;

import com.raybyte.jstore.dto.UserDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {

    private static final String ROLE_PREFIX = "ROLE_";

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    protected void init() {
        //register test user while application is started.
        register("admin", "123456", Set.of("ADMIN", "USER"));
        register("user", "123456", Set.of("USER"));
    }

    public Optional<User> findByUsername(String username) {
        Optional<UserDO> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent()) {
            UserDO userDO = userOptional.get();
            List<SimpleGrantedAuthority> authorities = userDO.getRoles().stream().map(role -> new SimpleGrantedAuthority(ROLE_PREFIX + role)).collect(Collectors.toList());
            return Optional.of(new User(userDO.getUsername(), userDO.getPassword(), authorities));
        }
        return Optional.empty();
    }

    public void register(String username, String password, Set<String> roles) {
        userRepository.register(new UserDO(username, passwordEncoder.encode(password), roles));
    }
}