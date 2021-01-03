package compeng.arved.service;

import compeng.arved.domain.Article;
import compeng.arved.payload.ArticlePayload;
import compeng.arved.repository.ArticleRepository;
import compeng.arved.repository.StaffRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleServiceImpl implements ArticleService{
    private final ArticleRepository articleRepository;
    private final StaffRepository staffRepository;

    public ArticleServiceImpl(ArticleRepository articleRepository, StaffRepository staffRepository) {
        this.articleRepository = articleRepository;
        this.staffRepository = staffRepository;
    }

    @Override
    public List<Article> getArticles(Authentication authentication) {

        return null;
    }

    @Override
    public Article save(Article article) {
        return null;
    }

    @Override
    public void add(ArticlePayload articlePayload) {

    }

    @Override
    public void update(ArticlePayload articlePayload, String id) {

    }

    @Override
    public void deleteById(String id) {

    }

    @Override
    public Optional<Article> findById(String id) {
        return Optional.empty();
    }
}
