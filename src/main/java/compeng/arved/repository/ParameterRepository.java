package compeng.arved.repository;

import compeng.arved.domain.Article;
import compeng.arved.domain.Parameter;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface ParameterRepository extends MongoRepository<Parameter, String> {

    @Query("{'paramId' : ?0}")
    Parameter findParameterByParamId(String paramId);
}
