package compeng.arved.repository;

import compeng.arved.domain.Photo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PhotoRepository extends MongoRepository<Photo, String> {
}
