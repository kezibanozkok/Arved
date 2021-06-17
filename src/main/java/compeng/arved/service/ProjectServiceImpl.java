package compeng.arved.service;

import compeng.arved.domain.Project;
import compeng.arved.domain.User;
import compeng.arved.payload.ProjectPayload;
import compeng.arved.repository.ProjectRepository;
import compeng.arved.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class ProjectServiceImpl implements ProjectService{
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository, UserRepository userRepository) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Project> getUserProjects(Authentication authentication) {
        String email = authentication.getName();
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return projectRepository.findProjectByUserId(user.getId());
        }
        return null;
    }

    @Override
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    /*@Override
    public List<Project> getProjectsByUserId(String userId) {
        return projectRepository.findProjectByUserId(userId);
    }*/

    @Override
    public Project save(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public void add(ProjectPayload projectPayload, Authentication authentication) {
        String email = authentication.getName();
        Optional<User> optionalUser = userRepository.findByEmail(email);
        String userId;

        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        String projectId = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        if (optionalUser.isPresent()) {
            userId = optionalUser.get().getId();
            Project project = new Project(null, projectId, userId, projectPayload.isBap(), projectPayload.isUluslararasi(), projectPayload.getYil(), projectPayload.getProjeDurumu(), projectPayload.getProjeTuru(), projectPayload.getAlanBilgisi(), projectPayload.getProjeAdi(), projectPayload.getProjeButcesi(), projectPayload.getParaBirimi(),
                    projectPayload.isKontratliProje(), projectPayload.isDisDestekli(), projectPayload.isUluslararasiIsbirlikli(), projectPayload.getArastirmaciSayisi(), projectPayload.getProjeYurutucusu());
            projectRepository.save(project);
        }
    }

    @Override
    public void update(ProjectPayload projectPayload, String projeId) {
        Project project = projectRepository.findProjectByProjeId(projeId);
        project.setBap(projectPayload.isBap());
        project.setUluslararasi(projectPayload.isUluslararasi());
        project.setYil(projectPayload.getYil());
        project.setProjeDurumu(projectPayload.getProjeDurumu());
        project.setProjeTuru(projectPayload.getProjeTuru());
        project.setAlanBilgisi(projectPayload.getAlanBilgisi());
        project.setProjeAdi(projectPayload.getProjeAdi());
        project.setProjeButcesi(projectPayload.getProjeButcesi());
        project.setParaBirimi(projectPayload.getParaBirimi());
        project.setKontratliProje(projectPayload.isKontratliProje());
        project.setDisDestekli(projectPayload.isDisDestekli());
        project.setUluslararasiIsbirlikli(projectPayload.isUluslararasiIsbirlikli());
        project.setArastirmaciSayisi(projectPayload.getArastirmaciSayisi());
        project.setProjeYurutucusu(projectPayload.getProjeYurutucusu());
        projectRepository.save(project);
    }

    @Override
    public void deleteById(String projeId) {
        projectRepository.deleteProjectByProjeId(projeId);
    }

    /*@Override
    public Optional<Project> findById(String id) {
        return projectRepository.findById(id);
    }*/

    @Override
    public List<Project> getReport(String projeYil, boolean kurumIciProje, boolean uluslararasi, boolean kontratliProje) {
        return projectRepository.findProjectsByProjeYilOrBapOrUluslararasiOrKontratliProje(projeYil, kurumIciProje, uluslararasi, kontratliProje);
    }
}
