package compeng.arved.service;

import compeng.arved.domain.Project;
import compeng.arved.payload.ProjectPayload;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.Optional;

public interface ProjectService {

    List<Project> getProjects(Authentication authentication);
    Project save (Project project);
    void add(ProjectPayload projectPayload);
    void update(ProjectPayload projectPayload, String id);
    void deleteById(String id);
    Optional<Project> findById(String id);
}
