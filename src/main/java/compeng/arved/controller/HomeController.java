package compeng.arved.controller;

import compeng.arved.service.ArticleService;
import compeng.arved.service.ProjectService;
import compeng.arved.service.StaffInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
    public String getHomePage(Model model, Authentication authentication) {
        model.addAttribute("staffInformation", staffInformationService.getStaffInformation(authentication));
        model.addAttribute("articles", articleService.getUserArticles(authentication));
        model.addAttribute("projects", projectService.getUserProjects(authentication));
        return "home";
    }

    /*
    @GetMapping("/403")
    public String error() {
        return "403";
    }
    */
}

