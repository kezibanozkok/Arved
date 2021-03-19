package compeng.arved.service;

import compeng.arved.domain.StaffInformation;
import compeng.arved.domain.User;
import compeng.arved.domain.UserConfirmation;
import compeng.arved.repository.StaffInformationRepository;
import compeng.arved.repository.UserConfirmationRepository;
import compeng.arved.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class UserConfirmationServiceImpl implements UserConfirmationService{
    private final UserConfirmationRepository userConfirmationRepository;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final StaffInformationRepository staffInformationRepository;
    @Autowired
    public UserConfirmationServiceImpl(UserConfirmationRepository userConfirmationRepository, UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, StaffInformationRepository staffInformationRepository) {
        this.userConfirmationRepository = userConfirmationRepository;
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.staffInformationRepository = staffInformationRepository;
    }

    @Override
    public List<UserConfirmation> getPendingApproval() {
        return userConfirmationRepository.findAll();
    }

    @Override
    public void confirm(String id) {
        Optional<UserConfirmation> optionalUserConfirmation = userConfirmationRepository.findById(id);
        if (optionalUserConfirmation.isPresent()) {
            User user = new User();

            int leftLimit = 48; // numeral '0'
            int rightLimit = 122; // letter 'z'
            int targetStringLength = 10;
            Random random = new Random();

            String userId = random.ints(leftLimit, rightLimit + 1)
                    .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                    .limit(targetStringLength)
                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                    .toString();

            StaffInformation staffInformation = new StaffInformation(null, userId, "", 0, 0, 0, 0, "");
            user.setId(userId);
            user.setName(optionalUserConfirmation.get().getName());
            user.setSurname(optionalUserConfirmation.get().getSurname());
            user.setEmail(optionalUserConfirmation.get().getEmail());
            user.setPassword(bCryptPasswordEncoder.encode(optionalUserConfirmation.get().getPassword()));
            userRepository.save(user);
            userConfirmationRepository.deleteById(id);
            staffInformationRepository.save(staffInformation);
        }
    }

    @Override
    public void reject(String id) {
        userConfirmationRepository.deleteById(id);
    }


}
