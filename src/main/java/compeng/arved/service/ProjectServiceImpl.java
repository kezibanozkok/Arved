package compeng.arved.service;

import compeng.arved.domain.Project;
import compeng.arved.payload.ProjectPayload;
import compeng.arved.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService{
    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public List<Project> getProjects(Authentication authentication) {
        return null;
    }

    @Override
    public Project save(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public void add(ProjectPayload projectPayload) {

    }

    @Override
    public void update(ProjectPayload projectPayload, String id) {

    }

    @Override
    public void deleteById(String id) {
        projectRepository.deleteById(id);
    }

    @Override
    public Optional<Project> findById(String id) {
        return Optional.empty();
    }
}
