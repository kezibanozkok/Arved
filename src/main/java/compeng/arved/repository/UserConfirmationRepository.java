package compeng.arved.repository;

import compeng.arved.domain.UserConfirmation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserConfirmationRepository extends MongoRepository<UserConfirmation, String> {
}
