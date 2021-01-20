package compeng.arved.repository;

import compeng.arved.domain.Article;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArticleRepository extends MongoRepository<Article, String> {
    Optional<Article> findById (String id);
}
