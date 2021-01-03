package compeng.arved.service;

import org.springframework.web.multipart.MultipartFile;

public interface PhotoService {

    String uploadPhoto(String title, MultipartFile file);
}
