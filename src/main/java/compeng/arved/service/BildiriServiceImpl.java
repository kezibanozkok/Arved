package compeng.arved.service;

import compeng.arved.domain.Article;
import compeng.arved.domain.Bildiri;
import compeng.arved.domain.User;
import compeng.arved.payload.BildiriPayload;
import compeng.arved.repository.BildiriRepository;
import compeng.arved.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class BildiriServiceImpl implements BildiriService{
    private final BildiriRepository bildiriRepository;
    private final UserRepository userRepository;

    public BildiriServiceImpl(BildiriRepository bildiriRepository, UserRepository userRepository) {
        this.bildiriRepository = bildiriRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Bildiri> getUserBildiri(Authentication authentication) {
        String email = authentication.getName();
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return bildiriRepository.findBildiriByUserId(user.getId());
        }
        return null;
    }

    @Override
    public Bildiri save(Bildiri bildiri) {
        return bildiriRepository.save(bildiri);
    }

    @Override
    public void add(BildiriPayload bildiriPayload, Authentication authentication) {
        String email = authentication.getName();
        Optional<User> optionalUser = userRepository.findByEmail(email);
        String userId;

        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        String bildiriId = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        if (optionalUser.isPresent()) {
            userId = optionalUser.get().getId();
            Bildiri bildiri = new Bildiri(null, bildiriId, userId, bildiriPayload.getBaslik(), bildiriPayload.getYazar(), bildiriPayload.getKonferans(),
                    bildiriPayload.getUlke(), bildiriPayload.getSehir(), bildiriPayload.getYil(), bildiriPayload.getKonu());
            bildiriRepository.save(bildiri);
        }
    }

    @Override
    public void update(BildiriPayload bildiriPayload, String bildiriId) {
        Bildiri bildiri = bildiriRepository.findBildiriByBildiriId(bildiriId);
        bildiri.setBaslik(bildiriPayload.getBaslik());
        bildiri.setYazar(bildiriPayload.getYazar());
        bildiri.setKonferans(bildiriPayload.getKonferans());
        bildiri.setUlke(bildiriPayload.getUlke());
        bildiri.setSehir(bildiriPayload.getSehir());
        bildiri.setYil(bildiriPayload.getYil());
        bildiri.setKonu(bildiriPayload.getKonu());
        bildiriRepository.save(bildiri);
    }

    @Override
    public void deleteById(String bildiriId) {
        bildiriRepository.deleteBildiriByBildiriId(bildiriId);
    }

    @Override
    public List<Bildiri> getReport(String bildiriYil) {
        return bildiriRepository.findBildirisByYil(bildiriYil);
    }

}
