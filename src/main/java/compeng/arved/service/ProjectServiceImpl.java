package compeng.arved.service;

import compeng.arved.domain.Project;
import compeng.arved.domain.User;
import compeng.arved.payload.ProjectPayload;
import compeng.arved.repository.ProjectRepository;
import compeng.arved.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
            return user.getProjects();
        }
        return null;
    }

    @Override
    public Project save(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public void add(ProjectPayload projectPayload, Authentication authentication) {
        Project project = new Project(null, projectPayload.getProjeAdi(), projectPayload.getProjeYurutucusu(), projectPayload.getProjeninAmaci(), projectPayload.getProjeDurumu(), projectPayload.isKurumIciProje(), projectPayload.isUluslararasiProje(), projectPayload.getProjeTuru(), projectPayload.getAlanBilgisi(), projectPayload.getProjeButcesi(),
                projectPayload.getParaBirimi(), projectPayload.isKontratliProje(), projectPayload.isDisDestekliProje(), projectPayload.isUluslararasiIsbirlikliProje(), projectPayload.getArastirmaciSayisi());
        projectRepository.save(project);
        String email = authentication.getName();
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            List<Project> projectList = new ArrayList<>();
            projectList = user.getProjects();
            projectList.add(project);
            user.setProjects(projectList);
            //user.setProjects(Stream.of(project).collect(Collectors.toList()));
            userRepository.save(user);
        }
    }

    @Override
    public void update(ProjectPayload projectPayload, String id) {
        Optional<Project> optionalProject = projectRepository.findById(id);
        if (optionalProject.isPresent()) {
            Project project = optionalProject.get();
            project.setProjeAdi(projectPayload.getProjeAdi());
            project.setProjeYurutucusu(projectPayload.getProjeYurutucusu());
            project.setProjeninAmaci(projectPayload.getProjeninAmaci());
            project.setProjeDurumu(projectPayload.getProjeDurumu());
            project.setKurumIciProje(projectPayload.isKurumIciProje());
            project.setUluslararasiProje(projectPayload.isUluslararasiProje());
            project.setProjeTuru(projectPayload.getProjeTuru());
            project.setAlanBilgisi(projectPayload.getAlanBilgisi());
            project.setProjeButcesi(projectPayload.getProjeButcesi());
            project.setParaBirimi(projectPayload.getParaBirimi());
            project.setKontratliProje(projectPayload.isKontratliProje());
            project.setDisDestekliProje(projectPayload.isDisDestekliProje());
            project.setUluslararasiIsbirlikliProje(projectPayload.isUluslararasiIsbirlikliProje());
            project.setArastirmaciSayisi(projectPayload.getArastirmaciSayisi());
            projectRepository.save(project);
        }
    }

    @Override
    public void deleteById(String id) {
        projectRepository.deleteById(id);
    }

    @Override
    public Optional<Project> findById(String id) {
        return projectRepository.findById(id);
    }
}
