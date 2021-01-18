package compeng.arved.service;

import compeng.arved.domain.StaffInformation;
import compeng.arved.payload.StaffInformationPayload;
import org.springframework.security.core.Authentication;

public interface StaffInformationService {

    //it shows user's information that is logged in
    StaffInformation getInformation(Authentication authentication);

    //it saves staff information to database
    StaffInformation save(StaffInformation staffInformation);

    //staff information is updated
    void update(StaffInformationPayload staffInformationPayload);
}
