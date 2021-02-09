package compeng.arved.controller;

import compeng.arved.payload.ProjectPayload;
import compeng.arved.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @GetMapping("/update/{projeId}")
    public String updateProjectPage() {
        return "updateProject";
    }

    @PostMapping("/update/{projeId}")
    public String updateProject(@ModelAttribute ProjectPayload projectPayload, @PathVariable String projeId) {
        projectService.update(projectPayload, projeId);
        return "redirect:/home";
    }

    @PostMapping("/delete/{projeId}")
    public String deleteProject(@PathVariable String projeId) {
        projectService.deleteById(projeId);
        return "redirect:/home";
    }

    /*@GetMapping("/allProjects")
    public String getAllProjects(Model model) {
        model.addAttribute("projects", projectService.getAllProjects());
        return "allProjects";
    }*/
}
