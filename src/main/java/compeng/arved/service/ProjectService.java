package compeng.arved.service;

import compeng.arved.domain.Project;
import compeng.arved.payload.ProjectPayload;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.Optional;

public interface ProjectService {

    List<Project> getUserProjects(Authentication authentication);
    List<Project> getAllProjects();
    //public List<Project> getProjectsByUserId(String userId);
    Project save (Project project);
    void add(ProjectPayload projectPayload, Authentication authentication);
    void update(ProjectPayload projectPayload, String id);
    void deleteById(String id);
    //Optional<Project> findById(String id);
    List<Project> getReport(String projeYil, boolean kurumIciProje, boolean uluslararasi, boolean kontratliProje);
}
