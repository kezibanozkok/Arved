package compeng.arved.controller;

import compeng.arved.payload.ArticlePayload;
import compeng.arved.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
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

    @GetMapping("/update/{id}")
    public String updateArticlePage() {
        return "updateArticle";
    }

    @PostMapping("/update/{id}")
    public String updateArticle(@ModelAttribute ArticlePayload articlePayload, @PathVariable String id) {
        articleService.update(articlePayload, id);
        return "redirect:/home";
    }

    @PostMapping("/delete/{id}")
    public String deleteArticle(Authentication authentication, @PathVariable String id) {
        articleService.deleteById(authentication, id);
        return "redirect:/home";
    }
}
