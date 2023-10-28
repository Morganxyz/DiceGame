package ItAcademy521.Sprint521.Model.Repository;
import ItAcademy521.Sprint521.Model.Entities.Player;
import org.bson.types.ObjectId;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends MongoRepository<Player, ObjectId> {
    boolean existsById(ObjectId id);

}
