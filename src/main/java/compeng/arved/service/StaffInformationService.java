package compeng.arved.service;

import compeng.arved.domain.StaffInformation;
import compeng.arved.payload.StaffInformationPayload;
import org.springframework.security.core.Authentication;

public interface StaffInformationService {

    StaffInformation getInformation(Authentication authentication);
    StaffInformation save(StaffInformation staffInformation);
    void update(StaffInformationPayload staffInformationPayload);
}
