package compeng.arved.controller;

import compeng.arved.payload.BildiriPayload;
import compeng.arved.service.BildiriService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("bildiri")
public class BildiriController {
    private final BildiriService bildiriService;

    public BildiriController(BildiriService bildiriService) {
        this.bildiriService = bildiriService;
    }

    @GetMapping("/add")
    String addBildiriPage() {
        return "addBildiri";
    }

    @PostMapping("/add")
    String addBildiri(@ModelAttribute BildiriPayload bildiriPayload, Authentication authentication) {
        bildiriService.add(bildiriPayload, authentication);
        return "redirect:/home";
    }

    @GetMapping("/update/{bildiriId}")
    String updateBildiriPage() {
        return "updateBildiri";
    }

    @PostMapping("/update/{bildiriId}")
    String updateBildiri(@ModelAttribute BildiriPayload bildiriPayload, @PathVariable String bildiriId) {
        bildiriService.update(bildiriPayload, bildiriId);
        return "redirect:/home";
    }

    @PostMapping("/delete/{bildiriId}")
    String deleteBildiri(@PathVariable String bildiriId) {
        bildiriService.deleteById(bildiriId);
        return "redirect:/home";
    }
}
