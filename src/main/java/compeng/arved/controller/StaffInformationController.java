package compeng.arved.controller;

import compeng.arved.payload.StaffInformationPayload;
import compeng.arved.service.StaffInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/staffInformation")
public class StaffInformationController {

    private final StaffInformationService staffInformationService;

    @Autowired
    public StaffInformationController(StaffInformationService staffInformationService) {
        this.staffInformationService = staffInformationService;
    }

    @GetMapping("/update")
    public String updateInformationPage() {
        return "updateStaffInformation";
    }

    @PostMapping("/update")
    public String updateInformation(@ModelAttribute StaffInformationPayload staffInformationPayload, Authentication authentication) {
        staffInformationService.update(staffInformationPayload, authentication);
        return "redirect:/home";
    }
}
