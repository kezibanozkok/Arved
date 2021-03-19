package compeng.arved.controller;

import compeng.arved.domain.User;
import compeng.arved.payload.UserPayload;
import compeng.arved.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class SecurityController {
    private final UserService userService;

    @Autowired
    public SecurityController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/login?error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }

    @GetMapping("/registration")
    public String showRegistrationForm() {
        return "registration";
    }

    @PostMapping("/registration")
    public String register(@ModelAttribute @Valid UserPayload userPayload, HttpServletRequest request, RedirectAttributes redirectAttributes) {

        if (userService.register(userPayload)) {
            redirectAttributes.addFlashAttribute("success", "Kayıt isteğiniz alınmıştır. Kaydınız onaylandıktan sonra giriş yapabilirsiniz.");
            return "redirect:/registration";
        } else {
            redirectAttributes.addFlashAttribute("fail", "Bir hata oluştu.");
            return "redirect:/registration";
        }
    }
}
