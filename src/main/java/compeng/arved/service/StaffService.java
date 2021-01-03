package compeng.arved.service;

import compeng.arved.payload.StaffPayload;
import org.springframework.web.multipart.MultipartFile;

public interface StaffService {

    void register(StaffPayload staffPayload);
    void changePassword(StaffPayload staffPayload, String id);

}
