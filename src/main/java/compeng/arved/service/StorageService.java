package compeng.arved.service;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

    //uploads document
    void store(MultipartFile file);
}
