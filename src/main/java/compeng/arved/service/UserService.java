package compeng.arved.service;

import compeng.arved.domain.User;
import compeng.arved.payload.UserPayload;

import java.util.Optional;

public interface UserService {

    void register(UserPayload userPayload);
    User save (User user);
    void changePassword(UserPayload userPayload, String id);
    Optional<User> findByEmail(String email);
}
