package compeng.arved.service;

import compeng.arved.domain.*;
import compeng.arved.payload.UserPayload;
import compeng.arved.repository.UserConfirmationRepository;
import compeng.arved.repository.UserRepository;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserConfirmationRepository userConfirmationRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, UserConfirmationRepository userConfirmationRepository) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userConfirmationRepository = userConfirmationRepository;
    }

    @Override
    public boolean register(UserPayload userPayload) {

        UserConfirmation userConfirmation = new UserConfirmation(null, userPayload.getName(), userPayload.getSurname(), userPayload.getEmail(), userPayload.getPassword());
        if (Strings.isNotBlank(userConfirmation.getName()) && Strings.isNotBlank(userConfirmation.getSurname()) && Strings.isNotBlank(userConfirmation.getEmail()) && Strings.isNotBlank(userConfirmation.getPassword())) {
            userConfirmationRepository.save(userConfirmation);
            return true;
        }
        return false;
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByEmail(email);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            return org.springframework.security.core.userdetails.User.builder()
                    .username(user.getEmail())
                    .password(user.getPassword())
                    .disabled(false)
                    .accountExpired(false)
                    .credentialsExpired(false)
                    .roles()
                    .build();
        } else {
            throw new UsernameNotFoundException("not found");
        }
    }
}
