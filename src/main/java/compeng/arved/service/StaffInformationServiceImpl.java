package compeng.arved.service;

import compeng.arved.domain.StaffInformation;
import compeng.arved.domain.User;
import compeng.arved.payload.StaffInformationPayload;
import compeng.arved.repository.StaffInformationRepository;
import compeng.arved.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public StaffInformation getStaffInformation(Authentication authentication) {
        String email = authentication.getName();
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return staffInformationRepository.findStaffInformationByUserId(user.getId());
        } else {
            return null;
        }
    }

    @Override
    public List<StaffInformation> getAllStaff() {
        return staffInformationRepository.findAll();
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
            if (staffInformationRepository.findStaffInformationByUserId(user.getId()) == null) {
                StaffInformation staffInformation = new StaffInformation(null, user.getId(), staffInformationPayload.getFullName(),
                        staffInformationPayload.getWosHIndex(), staffInformationPayload.getWosAtifSayisi(),
                        staffInformationPayload.getScopusHIndex(), staffInformationPayload.getScopusAtifSayisi(),
                        staffInformationPayload.getUzmanlikAlani());
                staffInformationRepository.save(staffInformation);
            } else {
                StaffInformation staffInformation = staffInformationRepository.findStaffInformationByUserId(user.getId());
                staffInformation.setFullName(staffInformationPayload.getFullName());
                staffInformation.setWosHIndex(staffInformationPayload.getWosHIndex());
                staffInformation.setWosAtifSayisi(staffInformationPayload.getWosAtifSayisi());
                staffInformation.setScopusHIndex(staffInformationPayload.getScopusHIndex());
                staffInformation.setScopusAtifSayisi(staffInformationPayload.getScopusAtifSayisi());
                staffInformation.setUzmanlikAlani(staffInformationPayload.getUzmanlikAlani());
                staffInformationRepository.save(staffInformation);
            }
        }
    }
}
