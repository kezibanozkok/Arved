package compeng.arved.service;

import compeng.arved.domain.Article;
import compeng.arved.payload.ArticlePayload;
import compeng.arved.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleServiceImpl implements ArticleService{
    private final ArticleRepository articleRepository;

    @Autowired
    public ArticleServiceImpl(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public List<Article> getArticles(Authentication authentication) {
        String email = authentication.getName();
        return null;
    }

    @Override
    public Article save(Article article) {
        return articleRepository.save(article);
    }

    @Override
    public void add(ArticlePayload articlePayload) {

    }

    @Override
    public void update(ArticlePayload articlePayload, String id) {

    }

    @Override
    public void deleteById(String id) {
        articleRepository.deleteById(id);
    }

    @Override
    public Optional<Article> findById(String id) {
        return articleRepository.findById(id);
    }
}
