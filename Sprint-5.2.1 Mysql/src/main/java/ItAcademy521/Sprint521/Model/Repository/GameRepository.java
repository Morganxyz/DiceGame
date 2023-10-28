package ItAcademy521.Sprint521.Model.Repository;

import ItAcademy521.Sprint521.Model.Entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

}
