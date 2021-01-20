package compeng.arved.service;

import compeng.arved.domain.StaffInformation;
import compeng.arved.domain.User;
import compeng.arved.payload.StaffInformationPayload;
import compeng.arved.repository.StaffInformationRepository;
import compeng.arved.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StaffInformationServiceImpl implements StaffInformationService{
    private final StaffInformationRepository staffInformationRepository;
    private final UserRepository userRepository;

    @Autowired
    public StaffInformationServiceImpl(StaffInformationRepository staffInformationRepository, UserRepository userRepository) {
        this.staffInformationRepository = staffInformationRepository;
        this.userRepository = userRepository;
    }

    @Override
    public StaffInformation getInformation(Authentication authentication) {
        String email = authentication.getName();
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return user.getStaffInformation();
        } else {
            return null;
        }
    }

    @Override
    public StaffInformation save(StaffInformation staffInformation) {
        return staffInformationRepository.save(staffInformation);
    }

    @Override
    public void update(StaffInformationPayload staffInformationPayload, Authentication authentication) {
        String email = authentication.getName();
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            StaffInformation staffInformation = new StaffInformation(null,staffInformationPayload.getWosHIndex(), staffInformationPayload.getWosAtifSayisi(), staffInformationPayload.getScopusHIndex(), staffInformationPayload.getScopusAtifSayisi(), staffInformationPayload.getUzmanlikAlani());
            user.setStaffInformation(staffInformation);
        }
    }
}
