package compeng.arved.controller;

import compeng.arved.payload.ArticlePayload;
import compeng.arved.payload.ProjectPayload;
import compeng.arved.payload.StaffInformationPayload;
import compeng.arved.service.ArticleService;
import compeng.arved.service.ProjectService;
import compeng.arved.service.StaffInformationService;
import compeng.arved.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/home")
public class HomeController {

    private final StaffInformationService staffInformationService;
    private final ArticleService articleService;
    private final ProjectService projectService;

    @Autowired
    public HomeController(StaffInformationService staffInformationService, ArticleService articleService, ProjectService projectService) {
        this.staffInformationService = staffInformationService;
        this.articleService = articleService;
        this.projectService = projectService;
    }

    @GetMapping()
    public String getStaffInformation(Model model, Authentication authentication) {
        model.addAttribute("staffInformation", staffInformationService.getStaffInformation(authentication));
        model.addAttribute("articles", articleService.getUserArticles(authentication));
        model.addAttribute("projects", projectService.getUserProjects(authentication));
        return "home";
    }

    /*@GetMapping("/updateInformation")
    public String updateStaffInformation() {
        return "updateStaffInformation";
    }

    @PostMapping("/updateInformation")
    public String updateStaffInformation(@ModelAttribute StaffInformationPayload staffInformationPayload, Authentication authentication) {
        userService.updateStaffInformation(staffInformationPayload, authentication);
        return "redirect:/home";
    }*/

    /*@GetMapping("/addProject")
    public String addProjectPage() {
        return "addProject";
    }

    @PostMapping("/addProject")
    public String addProject(@ModelAttribute ProjectPayload projectPayload, Authentication authentication) {
        userService.addProject(projectPayload, authentication);
        return "redirect:/home";
    }

    @GetMapping("/updateProject/{id}")
    public String updateProjectPage() {
        return "updateProject";
    }

    @PostMapping("/updateProject/{id}")
    public String updateProject(@ModelAttribute ProjectPayload projectPayload, Authentication authentication, @PathVariable String id) {
        userService.updateProject(projectPayload, authentication, id);
        return "redirect:/home";
    }*/

    /*
    @GetMapping("/403")
    public String error() {
        return "403";
    }
    */
}

