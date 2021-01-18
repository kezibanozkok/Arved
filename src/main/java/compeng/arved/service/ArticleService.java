package compeng.arved.service;

import compeng.arved.domain.Article;
import compeng.arved.payload.ArticlePayload;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.Optional;

public interface ArticleService {

    //it takes logged in user's information as a parameter and returns list of
    //that user's articles
    List<Article> getArticles(Authentication authentication);

    //it saves article to database
    Article save (Article article);

    //it takes the article information via ArticlePayload
    void add(ArticlePayload articlePayload);

    //with this method, it can be changed information of article according to "id" of the article
    void update(ArticlePayload articlePayload, String id);

    //it deletes article according to id
    void deleteById(String id);

    //it shows selected article's summary
    Optional<Article> findById(String id);
}
