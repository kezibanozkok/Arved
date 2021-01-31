package compeng.arved.service;

import compeng.arved.domain.Article;
import compeng.arved.payload.ArticlePayload;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.Optional;

public interface ArticleService {

    List<Article> getUserArticles(Authentication authentication);
    Article save (Article article);
    void add(ArticlePayload articlePayload, Authentication authentication);
    void update(ArticlePayload articlePayload, String id);
    void deleteById(Authentication authentication, String id);
    Optional<Article> findById(String id);
}
