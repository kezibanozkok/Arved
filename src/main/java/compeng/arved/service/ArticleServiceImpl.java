package compeng.arved.service;

import compeng.arved.domain.Article;
import compeng.arved.domain.User;
import compeng.arved.payload.ArticlePayload;
import compeng.arved.repository.ArticleRepository;
import compeng.arved.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ArticleServiceImpl implements ArticleService{
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;

    @Autowired
    public ArticleServiceImpl(ArticleRepository articleRepository, UserRepository userRepository) {
        this.articleRepository = articleRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Article> getUserArticles(Authentication authentication) {
        String email = authentication.getName();
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return user.getArticles();
        }
        return null;
    }

    @Override
    public Article save(Article article) {
        return articleRepository.save(article);
    }

    @Override
    public void add(ArticlePayload articlePayload, Authentication authentication) {
        Article article = new Article(null, articlePayload.getYayinTuru(), articlePayload.getEndeksTuru(), articlePayload.getBaslik(), articlePayload.getYazarlar(), articlePayload.getYil(), articlePayload.getDergiAdi(),
                articlePayload.getKonferans(), articlePayload.getCilt(), articlePayload.getSayi(), articlePayload.getSayfa(), articlePayload.getDoi());
        articleRepository.save(article);

        String email = authentication.getName();
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setArticles(Stream.of(article).collect(Collectors.toList()));
            userRepository.save(user);
        }

    }

    @Override
    public void update(ArticlePayload articlePayload, String id) {
        Optional<Article> optionalArticle = articleRepository.findById(id);
        if (optionalArticle.isPresent()) {
            Article article = optionalArticle.get();
            article.setBaslik(articlePayload.getBaslik());
            article.setCilt(articlePayload.getCilt());
            article.setDergiAdi(articlePayload.getDergiAdi());
            article.setDoi(articlePayload.getDoi());
            article.setEndeksTuru(articlePayload.getEndeksTuru());
            article.setKonferans(articlePayload.getKonferans());
            article.setSayfa(articlePayload.getSayfa());
            article.setSayi(articlePayload.getSayi());
            article.setYayinTuru(articlePayload.getYayinTuru());
            article.setYil(articlePayload.getYil());
            article.setYazarlar(articlePayload.getYazarlar());
            articleRepository.save(article);
        }
    }

    @Override
    public void deleteById(Authentication authentication, String id) {
        articleRepository.deleteById(id);
        String email = authentication.getName();
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            userRepository.save(user);
        }
    }

    @Override
    public Optional<Article> findById(String id) {
        return articleRepository.findById(id);
    }
}
