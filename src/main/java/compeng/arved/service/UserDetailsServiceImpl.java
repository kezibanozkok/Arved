package compeng.arved.service;

import compeng.arved.domain.*;
import compeng.arved.payload.UserPayload;
import compeng.arved.repository.ArticleRepository;
import compeng.arved.repository.StaffInformationRepository;
import compeng.arved.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class UserDetailsServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final StaffInformationRepository staffInformationRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, StaffInformationRepository staffInformationRepository) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.staffInformationRepository = staffInformationRepository;
    }

    @Override
    public void register(UserPayload userPayload) {
        //StaffInformation staffInformation = new StaffInformation();
        List<Article> articleList = new ArrayList<>();
        List<Project> projectList = new ArrayList<>();
        Role role = new Role();
        role.setId(Long.parseLong("1"));
        role.setName("USER");
        List<Role> roles = new ArrayList<>();
        roles.add(role);

        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        String userId = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        User user = new User(null, userId, userPayload.getName(), userPayload.getSurname(), userPayload.getEmail(), bCryptPasswordEncoder.encode(userPayload.getPassword()));
        userRepository.save(user);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void changePassword(UserPayload userPayload, String id) {

    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }



    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByEmail(email);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            //List<String> roleList = new ArrayList<>();
            //user.getRoles().stream().forEach(role -> roleList.add(role.getName()));

            //boolean result = passwordEncoder.matches(password_plan_text_here, encoded_password_here);

            return org.springframework.security.core.userdetails.User.builder()
                    .username(user.getEmail())
                    .password(user.getPassword())
                    .disabled(false)
                    .accountExpired(false)
                    .credentialsExpired(false)
                    .roles()
                    .build();
        } else {
            throw new UsernameNotFoundException("not found");
        }
    }

    /*@Override
    public StaffInformation getStaffInformation(Authentication authentication) {
        String email = authentication.getName();
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return user.getStaffInformation();
        } else {
            return null;
        }
    }*/

    /*@Override
    public void updateStaffInformation(StaffInformationPayload staffInformationPayload, Authentication authentication) {
        String email = authentication.getName();
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            StaffInformation staffInformation = new StaffInformation(null, staffInformationPayload.getWosHIndex(), staffInformationPayload.getWosAtifSayisi(), staffInformationPayload.getScopusHIndex(), staffInformationPayload.getScopusAtifSayisi(), staffInformationPayload.getUzmanlikAlani());
            user.setStaffInformation(staffInformation);
            mongoTemplate.save(user, "users");
        }
    }*/

    /*@Override
    public List<Article> getArticles(Authentication authentication) {
        String email = authentication.getName();
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return user.getArticles();
        } else {
            return null;
        }
    }*/

    /*@Override
    public void addArticle(ArticlePayload articlePayload, Authentication authentication) {
        String email = authentication.getName();
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            Article article = new Article(null, articlePayload.getYayinTuru(), articlePayload.getEndeksTuru(), articlePayload.getBaslik(), articlePayload.getYazarlar(), articlePayload.getDate(), articlePayload.getDergiAdi(),
                    articlePayload.getKonferans(), articlePayload.getCilt(), articlePayload.getSayi(), articlePayload.getSayfa(), articlePayload.getDoi());
            List<Article> articleList = new ArrayList<>();
            articleList = user.getArticles();
            articleList.add(article);
            user.setArticles(articleList);
            mongoTemplate.save(user, "users");
        }
    }*/

    /*@Override
    public void updateArticle(ArticlePayload articlePayload, Authentication authentication, String id) {
        String email = authentication.getName();
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

        }
    }*/

    /*@Override
    public List<Project> getProjects(Authentication authentication) {
        String email = authentication.getName();
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return user.getProjects();
        } else {
            return null;
        }
    }*/

    /*@Override
    public void addProject(ProjectPayload projectPayload, Authentication authentication) {
        String email = authentication.getName();
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            Project project = new Project(null, projectPayload.getProjeAdi(), projectPayload.getProjeYurutucusu(), projectPayload.getProjeninAmaci(), projectPayload.getProjeDurumu(), projectPayload.isKurumIciProje(), projectPayload.isUluslararasiProje(), projectPayload.getProjeTuru(), projectPayload.getAlanBilgisi(), projectPayload.getProjeButcesi(),
                    projectPayload.getParaBirimi(), projectPayload.isKontratliProje(), projectPayload.isDisDestekliProje(), projectPayload.isUluslararasiIsbirlikliProje(), projectPayload.getArastirmaciSayisi());
            List<Project> projectList = new ArrayList<>();
            projectList = user.getProjects();
            projectList.add(project);
            user.setProjects(projectList);
            mongoTemplate.save(user, "users");
        }
    }*/

    /*@Override
    public void updateProject(ProjectPayload projectPayload, Authentication authentication, String id) {

    }*/
}
