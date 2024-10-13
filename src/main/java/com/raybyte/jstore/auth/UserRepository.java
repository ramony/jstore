package com.raybyte.jstore.auth;

import com.raybyte.jstore.dto.UserDO;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class UserRepository {

    private static final Map<String, UserDO> allUsers = new HashMap<>();
    public Optional<UserDO> findByUsername(String username) {
        return Optional.ofNullable(allUsers.get(username));
    }

    public void register(UserDO userDO) {
        allUsers.put(userDO.getUsername(), userDO);
    }
}