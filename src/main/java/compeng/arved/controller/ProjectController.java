package compeng.arved.controller;

import compeng.arved.payload.ProjectPayload;
import compeng.arved.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/project")
public class ProjectController {

    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/add")
    public String addProjectPage() {
        return "addProject";
    }

    @PostMapping("add")
    public String addProject(@ModelAttribute ProjectPayload projectPayload, Authentication authentication) {
        projectService.add(projectPayload, authentication);
        return "redirect:/home";
    }

    @GetMapping("/update/{id}")
    public String updateProjectPage() {
        return "updateProject";
    }

    @PostMapping("/update/{id}")
    public String updateProject(@ModelAttribute ProjectPayload projectPayload, @PathVariable String id) {
        projectService.update(projectPayload, id);
        return "redirect:/home";
    }

    @PostMapping("/delete/{id}")
    public String deleteProject(@PathVariable String id) {
        projectService.deleteById(id);
        return "redirect:/home";
    }
}
