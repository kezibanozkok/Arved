package compeng.arved.controller;

import compeng.arved.service.ArticleService;
import compeng.arved.service.ProjectService;
import compeng.arved.service.StaffInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
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

    /*
    @GetMapping(value = {"/", "/home"})
    public String homePage() {
        return "home";
    }*/

    @GetMapping(value = {"/", "/home"})
    public String getStaffInformation(Model model, Authentication authentication) {
        model.addAttribute("staffInformation", staffInformationService.getInformation(authentication));
        model.addAttribute("article", articleService.getArticles(authentication));
        model.addAttribute("project", projectService.getProjects(authentication));
        return "home";
    }

    @GetMapping("/updateInformation")
    public String updateStaffInformation() {
        return "updateStaffInformation";
    }

    @PostMapping("/update/{id}")
    public String updateCustomer(@ModelAttribute CustomerPayload customerPayload, @PathVariable Long id) {
        customerService.update(customerPayload, id);
        return "redirect:/customers";
    }
    /*
    @GetMapping("/403")
    public String error() {
        return "403";
    }
    */
}

