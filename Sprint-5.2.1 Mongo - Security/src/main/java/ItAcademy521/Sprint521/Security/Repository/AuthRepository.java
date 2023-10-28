package ItAcademy521.Sprint521.Security.Repository;

import ItAcademy521.Sprint521.Security.User.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthRepository extends MongoRepository<User, ObjectId> {
    Optional<User> findByUsername(String username);
}
