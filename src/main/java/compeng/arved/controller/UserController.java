package compeng.arved.controller;

import compeng.arved.service.StaffInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/staff")
public class UserController {

    private final StaffInformationService staffInformationService;

    @Autowired
    public UserController(StaffInformationService staffInformationService) {
        this.staffInformationService = staffInformationService;
    }

    @GetMapping("/allStaff")
    public String getAllProjects(Model model) {
        model.addAttribute("staff", staffInformationService.getAllStaff());
        return "allStaff";
    }
}
