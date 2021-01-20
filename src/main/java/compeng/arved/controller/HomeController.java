package compeng.arved.controller;

import compeng.arved.payload.ArticlePayload;
import compeng.arved.payload.ProjectPayload;
import compeng.arved.payload.StaffInformationPayload;
import compeng.arved.service.ArticleService;
import compeng.arved.service.ProjectService;
import compeng.arved.service.StaffInformationService;
import compeng.arved.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {

    private final StaffInformationService staffInformationService;
    private final ArticleService articleService;
    private final ProjectService projectService;
    private final UserService userService;

    @Autowired
    public HomeController(StaffInformationService staffInformationService, ArticleService articleService, ProjectService projectService, UserService userService) {
        this.staffInformationService = staffInformationService;
        this.articleService = articleService;
        this.projectService = projectService;
        this.userService = userService;
    }

    /*
    @GetMapping(value = {"/", "/home"})
    public String homePage() {
        return "home";
    }*/

    @GetMapping(value = {"/", "/home"})
    public String getStaffInformation(Model model, Authentication authentication) {
        model.addAttribute("staffInformation", userService.getStaffInformation(authentication));
        model.addAttribute("articles", userService.getArticles(authentication));
        model.addAttribute("projects", userService.getProjects(authentication));
        return "home";
    }

    @GetMapping("/updateInformation")
    public String updateStaffInformation() {
        return "updateStaffInformation";
    }

    @PostMapping("/updateInformation")
    public String updateStaffInformation(@ModelAttribute StaffInformationPayload staffInformationPayload, Authentication authentication) {
        userService.updateStaffInformation(staffInformationPayload, authentication);
        return "redirect:/home";
    }

    @GetMapping("/addArticle")
    public String addArticlePage() {
        return "addArticle";
    }

    @PostMapping("/addArticle")
    public String addArticle(@ModelAttribute ArticlePayload articlePayload, Authentication authentication) {
        userService.addArticle(articlePayload, authentication);
        return "redirect:/home";
    }

    @GetMapping("/updateArticle/{id}")
    public String updateArticlePage() {
        return "updateArticle";
    }

    @PostMapping("/updateArticle/{id}")
    public String updateArticle(@ModelAttribute ArticlePayload articlePayload, Authentication authentication, @PathVariable String id) {
        userService.updateArticle(articlePayload, authentication, id);
        return "redirect:/home";
    }

    @GetMapping("/addProject")
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
    }

    /*
    @GetMapping("/403")
    public String error() {
        return "403";
    }
    */
}

