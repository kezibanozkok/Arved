package compeng.arved.controller;

import compeng.arved.domain.User;
import compeng.arved.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/home")
public class HomeController {

    private final StaffInformationService staffInformationService;
    private final ArticleService articleService;
    private final ProjectService projectService;
    private final BildiriService bildiriService;
    private final UserService userService;

    @Autowired
    public HomeController(StaffInformationService staffInformationService, ArticleService articleService, ProjectService projectService, BildiriService bildiriService, UserService userService) {
        this.staffInformationService = staffInformationService;
        this.articleService = articleService;
        this.projectService = projectService;
        this.bildiriService = bildiriService;
        this.userService = userService;
    }

    @GetMapping()
    public String getHomePage(Model model, Authentication authentication) {
        model.addAttribute("staffInformation", staffInformationService.getStaffInformation(authentication));
        model.addAttribute("articles", articleService.getUserArticles(authentication));
        model.addAttribute("projects", projectService.getUserProjects(authentication));
        model.addAttribute("bildiriler", bildiriService.getUserBildiri(authentication));
        Optional<User> optionalUser = userService.findByEmail(authentication.getName());
        User user = new User();
        if (optionalUser.isPresent()) {
            user = optionalUser.get();
        }
        model.addAttribute("userName", user.getName());
        model.addAttribute("userSurname", user.getSurname());
        return "home";
    }

    /*
    @GetMapping("/403")
    public String error() {
        return "403";
    }
    */
}

