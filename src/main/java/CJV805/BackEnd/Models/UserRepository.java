package CJV805.BackEnd.Models;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<UserModel, String> {

    UserModel findByUsername(String username);

}
