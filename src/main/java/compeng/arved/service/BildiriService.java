package compeng.arved.service;

import compeng.arved.domain.Bildiri;
import compeng.arved.payload.BildiriPayload;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface BildiriService {
    List<Bildiri> getUserBildiri(Authentication authentication);
    List<Bildiri> getAllBildiri();
    Bildiri save(Bildiri bildiri);
    void add(BildiriPayload bildiriPayload, Authentication authentication);
    void update(BildiriPayload bildiriPayload, String bildiriId);
    void deleteById(String bildiriId);
    List<Bildiri> getReport(String bildiriYil);
}
