package compeng.arved.service;

import compeng.arved.domain.Role;
import compeng.arved.domain.User;
import compeng.arved.payload.UserPayload;
import compeng.arved.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public void register(UserPayload userPayload) {
        Role role = new Role();
        role.setId(Long.parseLong("55"));
        role.setName("USER");
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        User user = new User(null, userPayload.getName(), userPayload.getSurname(), userPayload.getEmail(), userPayload.getPassword(), null, null, null, null, null, roles);
        userRepository.save(user);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void changePassword(UserPayload userPayload, String id) {

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
            List<String> roleList = new ArrayList<>();
            user.getRoles().stream().forEach(role -> roleList.add(role.getName()));

            return org.springframework.security.core.userdetails.User.builder()
                    .username(user.getEmail())
                    .password(bCryptPasswordEncoder.encode(user.getPassword()))
                    .disabled(false)
                    .accountExpired(false)
                    .credentialsExpired(false)
                    .roles(roleList.toArray(new String[0]))
                    .build();
        } else {
            throw new UsernameNotFoundException("not found");
        }
    }
}
