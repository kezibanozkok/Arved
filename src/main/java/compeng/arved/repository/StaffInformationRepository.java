package compeng.arved.repository;

import compeng.arved.domain.Project;
import compeng.arved.domain.StaffInformation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StaffInformationRepository extends MongoRepository<StaffInformation, String> {
    @Query("{'userId' : ?0}")
    StaffInformation findStaffInformationByUserId(String userId);

}
