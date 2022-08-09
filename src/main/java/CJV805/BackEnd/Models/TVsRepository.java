package CJV805.BackEnd.Models;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TVsRepository extends MongoRepository<TVs, String> {
}
