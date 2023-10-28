package ItAcademy521.Sprint521.Model.Repository;
import ItAcademy521.Sprint521.Model.Entities.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long>{
    boolean existsById(Long id);

}
