package compeng.arved.service;

import compeng.arved.domain.Article;
import compeng.arved.domain.Parameter;
import compeng.arved.domain.User;
import compeng.arved.payload.ArticlePayload;
import compeng.arved.repository.ArticleRepository;
import compeng.arved.repository.ParameterRepository;
import compeng.arved.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class ArticleServiceImpl implements ArticleService{
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;
    private final MongoTemplate mongoTemplate;
    private final ParameterRepository parameterRepository;

    @Autowired
    public ArticleServiceImpl(ArticleRepository articleRepository, UserRepository userRepository, MongoTemplate mongoTemplate, ParameterRepository parameterRepository) {
        this.articleRepository = articleRepository;
        this.userRepository = userRepository;
        this.mongoTemplate = mongoTemplate;
        this.parameterRepository = parameterRepository;
    }

    @Override
    public List<Article> getUserArticles(Authentication authentication) {
        String email = authentication.getName();
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return articleRepository.findArticleByUserId(user.getId());
        }
        return null;
    }

    @Override
    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    @Override
    public List<Article> getArticlesByUserId(String userId) {
        return articleRepository.findArticleByUserId(userId);
    }

    @Override
    public Article save(Article article) {
        return articleRepository.save(article);
    }

    @Override
    public void add(ArticlePayload articlePayload, Authentication authentication) {
        String email = authentication.getName();
        Optional<User> optionalUser = userRepository.findByEmail(email);
        String userId;

        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        String makaleId = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        if (optionalUser.isPresent()) {
            userId = optionalUser.get().getId();
            Article article = new Article(null, makaleId, userId, articlePayload.isUluslararasiYayin(), articlePayload.getEndeksTuru(), articlePayload.isUluslararasiIsbirlikli(), articlePayload.getMakaleAdi(), articlePayload.getDergiAdi(),
                    articlePayload.getYil(), articlePayload.getCilt_volume(), articlePayload.getSayi(), articlePayload.getSayfaNumarasi(), articlePayload.getDoi(), articlePayload.isBap(), articlePayload.isKurumDisiYazar(), articlePayload.getYazarListesi());
            articleRepository.save(article);
        }
    }

    @Override
    public void update(ArticlePayload articlePayload, String makaleId) {
        Article article = articleRepository.findArticleByMakaleId(makaleId);
        article.setUluslararasiYayin(articlePayload.isUluslararasiYayin());
        article.setEndeksTuru(articlePayload.getEndeksTuru());
        article.setUluslararasiIsbirlikli(articlePayload.isUluslararasiIsbirlikli());
        article.setMakaleAdi(articlePayload.getMakaleAdi());
        article.setDergiAdi(articlePayload.getDergiAdi());
        article.setYil(articlePayload.getYil());
        article.setCilt_volume(articlePayload.getCilt_volume());
        article.setSayi(articlePayload.getSayi());
        article.setSayfaNumarasi(articlePayload.getSayfaNumarasi());
        article.setDoi(articlePayload.getDoi());
        article.setBap(articlePayload.isBap());
        article.setKurumDisiYazar(articlePayload.isKurumDisiYazar());
        article.setYazarListesi(articlePayload.getYazarListesi());
        articleRepository.save(article);
    }

    @Override
    public void deleteById(String makaleId) {
        articleRepository.deleteArticleByMakaleId(makaleId);
    }

    @Override
    public Optional<Article> findById(String id) {
        return articleRepository.findById(id);
    }

    @Override
    public List<Article> getReport(String yil, String endeksTuru, boolean uluslararasiYayin, boolean bap) {
        return articleRepository.findArticlesByYilOrEndeksTuruOrUluslararasiYayinOrBap(yil, endeksTuru, uluslararasiYayin, bap);
    }

}
