package compeng.arved.controller;

import compeng.arved.domain.UserConfirmation;
import compeng.arved.service.ArticleService;
import compeng.arved.service.ProjectService;
import compeng.arved.service.StaffInformationService;
import compeng.arved.service.UserConfirmationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserConfirmationService userConfirmationService;
    private final StaffInformationService staffInformationService;
    private final ArticleService articleService;
    private final ProjectService projectService;

    @Autowired
    public AdminController(UserConfirmationService userConfirmationService, StaffInformationService staffInformationService, ArticleService articleService, ProjectService projectService) {
        this.userConfirmationService = userConfirmationService;
        this.staffInformationService = staffInformationService;
        this.articleService = articleService;
        this.projectService = projectService;
    }

    @GetMapping("/onayBekleyenler")
    public String getAdminPage(Model model) {
        model.addAttribute("pendingApproval", userConfirmationService.getPendingApproval());
        return "admin";
    }

    @PostMapping("/onayBekleyenler/confirm/{id}")
    public String confirmation(@PathVariable String id) {
        userConfirmationService.confirm(id);
        return "redirect:/admin/onayBekleyenler";
    }

    @PostMapping("/onayBekleyenler/delete/{id}")
    public String rejection(@PathVariable String id) {
        userConfirmationService.reject(id);
        return "redirect:/admin/onayBekleyenler";
    }

    @GetMapping("/allStaff")
    public String getAllStaff(Model model) {
        model.addAttribute("staff", staffInformationService.getAllStaff());
        return "allStaff";
    }

    @GetMapping("/allArticles")
    public String getAllArticles(Model model) {
        model.addAttribute("articles", articleService.getAllArticles());
        return "allArticles";
    }

    @GetMapping("/allProjects")
    public String getAllProjects(Model model) {
        model.addAttribute("projects", projectService.getAllProjects());
        return "allProjects";
    }
}
