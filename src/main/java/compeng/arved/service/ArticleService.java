package compeng.arved.service;

import compeng.arved.domain.Article;
import compeng.arved.payload.ArticlePayload;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.Optional;

public interface ArticleService {

    List<Article> getUserArticles(Authentication authentication);
    List<Article> getAllArticles();
    public List<Article> getArticlesByUserId(String userId);
    Article save (Article article);
    void add(ArticlePayload articlePayload, Authentication authentication);
    void update(ArticlePayload articlePayload, String id);
    void deleteById(String makaleId);
    Optional<Article> findById(String id);
    List<Article> getReport(String yil, String endeksTuru, boolean uluslararasiYayin, boolean bap);
}
