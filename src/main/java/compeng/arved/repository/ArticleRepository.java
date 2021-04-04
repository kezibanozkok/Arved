package compeng.arved.repository;

import compeng.arved.domain.Article;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ArticleRepository extends MongoRepository<Article, String> {
    @Query("{'makaleId' : ?0}")
    Article findArticleByMakaleId(String makaleId);

    @Query("{'userId' : ?0}")
    List<Article> findArticleByUserId(String userId);

    @Query(value = "{'makaleId' : ?0}", delete = true)
    void deleteArticleByMakaleId(String makaleId);

    @Query("{$and:["
            + " ?#{ [0]=='all' ? {$where:'true'} : {'yil':[0]} },"
            + " ?#{ [1]=='all' ? {$where:'true'} : {'endeksTuru':[1]} },"
            + " ?#{ [2]=='false' ? {$where:'true'} : {'uluslararasiYayin':[2]} },"
            + " ?#{ [3]=='false' ? {$where:'true'} : {'bap':[3]} },"
            + "]}")
    List<Article> findArticlesByYilOrEndeksTuruOrUluslararasiYayinOrBap(String yil, String endeksTuru, boolean uluslararasiYayin, boolean bap);
}
