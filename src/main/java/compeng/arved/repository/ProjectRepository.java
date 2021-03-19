package compeng.arved.repository;

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

    @Query("{$and :[ "
            + " ?#{ [0] == 'all' ? { $where : 'true'} : { 'projeYil' : [0] } },"
            + " ?#{ [1] == 'false' ? { $where : 'true'} : { 'kurumIciProje' : [1] } },"
            + " ?#{ [2] == 'false' ? { $where : 'true'} : { 'uluslararasi' : [2] } },"
            + " ?#{ [3] == 'false' ? { $where : 'true'} : { 'kontratliProje' : [3] } },"
            + "]}")
    List<Project> findProjectsByYilOrBapOrUluslararasiOrKontratliProje(String projeYil, boolean kurumIciProje, boolean uluslararasi, boolean kontratliProje);
}
