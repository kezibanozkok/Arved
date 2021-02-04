package compeng.arved.repository;

import compeng.arved.domain.Article;
import compeng.arved.domain.Project;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends MongoRepository<Project, String> {
    @Query("{'projeId' : ?0}")
    Project findProjectByProjeId(String projeId);

    @Query("{'userId' : ?0}")
    List<Project> findProjectByUserId(String userId);

    @Query(value = "{'projeId' : ?0}", delete = true)
    void deleteProjectByProjeId(String projeId);
}
