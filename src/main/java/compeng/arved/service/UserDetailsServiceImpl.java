package compeng.arved.service;

import compeng.arved.domain.*;
import compeng.arved.payload.UserPayload;
import compeng.arved.repository.UserConfirmationRepository;
import compeng.arved.repository.UserRepository;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserConfirmationRepository userConfirmationRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, UserConfirmationRepository userConfirmationRepository) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userConfirmationRepository = userConfirmationRepository;
    }

    @Override
    public boolean register(UserPayload userPayload) {
        //StaffInformation staffInformation = new StaffInformation();
        /*List<Article> articleList = new ArrayList<>();
        List<Project> projectList = new ArrayList<>();
        Role role = new Role();
        role.setId(Long.parseLong("1"));
        role.setName("USER");
        List<Role> roles = new ArrayList<>();
        roles.add(role);*/

        /*int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        String userId = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();*/
        //User user = new User(null, userId, userPayload.getName(), userPayload.getSurname(), userPayload.getEmail(), bCryptPasswordEncoder.encode(userPayload.getPassword()));

        /*public static boolean isValidUser(UserPayload userPayload) {
            return userPayload != null && Strings.isNotBlank(userPayload.getName())
                    && Strings.isNotBlank(userPayload.getSurname())
                    && Strings.isNotBlank(userPayload.getEmail())
                    && Strings.isNotBlank(userPayload.getPassword());
        }*/

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
            //List<String> roleList = new ArrayList<>();
            //user.getRoles().stream().forEach(role -> roleList.add(role.getName()));

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
