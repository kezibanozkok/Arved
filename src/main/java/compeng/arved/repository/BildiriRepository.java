package compeng.arved.repository;

import compeng.arved.domain.Bildiri;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BildiriRepository extends MongoRepository<Bildiri, String> {

    @Query("{'userId' : ?0}")
    List<Bildiri> findBildiriByUserId(String userId);

    @Query("{'bildiriId' : ?0}")
    Bildiri findBildiriByBildiriId(String bildiriId);

    @Query(value = "{'bildiriId' : ?0}", delete = true)
    void deleteBildiriByBildiriId(String bildiriId);

    @Query("{$and:["
            + " ?#{ [0]=='all' ? {$where:'true'} : {'yil':[0]} },"
            + "]}")
    List<Bildiri> findBildirisByYil(String bildiriYil);
}
