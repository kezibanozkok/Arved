package compeng.arved.repository;

import compeng.arved.domain.StaffInformation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffInformationRepository extends MongoRepository<StaffInformation, String> {

}
