package compeng.arved.service;

import compeng.arved.domain.User;
import compeng.arved.payload.UserPayload;
import java.util.Optional;

public interface UserService {

    boolean register(UserPayload userPayload);
    User save (User user);
    Optional<User> findByEmail(String email);
}
