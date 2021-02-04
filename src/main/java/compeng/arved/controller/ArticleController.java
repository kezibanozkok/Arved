package compeng.arved.controller;

import compeng.arved.payload.ArticlePayload;
import compeng.arved.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/article")
public class ArticleController {

    private final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/add")
    public String addArticlePage() {
        return "addArticle";
    }

    @PostMapping("/add")
    public String addArticle(@ModelAttribute ArticlePayload articlePayload, Authentication authentication) {
        articleService.add(articlePayload, authentication);
        return "redirect:/home";
    }

    @GetMapping("/update/{makaleId}")
    public String updateArticlePage() {
        return "updateArticle";
    }

    @PostMapping("/update/{makaleId}")
    public String updateArticle(@ModelAttribute ArticlePayload articlePayload, @PathVariable String makaleId) {
        articleService.update(articlePayload, makaleId);
        return "redirect:/home";
    }

    @PostMapping("/delete/{makaleId}")
    public String deleteArticle(@PathVariable String makaleId) {
        articleService.deleteById(makaleId);
        return "redirect:/home";
    }

    @GetMapping("/allArticles")
    public String getAllArticles(Model model) {
        model.addAttribute("articles", articleService.getAllArticles());
        return "allArticles";
    }
}
