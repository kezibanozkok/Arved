package compeng.arved.repository;

import compeng.arved.domain.Article;
import compeng.arved.domain.Project;
import compeng.arved.domain.User;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, Long> {
    Optional<User> findByEmail(String email);

}
