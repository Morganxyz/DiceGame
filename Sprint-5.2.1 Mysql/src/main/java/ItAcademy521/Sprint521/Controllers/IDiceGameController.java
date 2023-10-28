package ItAcademy521.Sprint521.Controllers;

import ItAcademy521.Sprint521.Model.Dto.GameDto;
import ItAcademy521.Sprint521.Model.Dto.PlayerDto;
import ItAcademy521.Sprint521.Model.Entities.Game;
import ItAcademy521.Sprint521.Model.Entities.Player;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface IDiceGameController {

    public ResponseEntity<Player> addPlayer(@RequestBody Player player);
    public ResponseEntity<Player>updatePlayer(@PathVariable long id, @RequestBody Player player);
    public ResponseEntity<?> deleteGamesListPlayer(@PathVariable long id);
    public ResponseEntity<List<PlayerDto>> getAllPlayersWithAveragePercent();
    public ResponseEntity<GameDto> newGame(@PathVariable long id);
    public ResponseEntity<List<GameDto>> listGamesPlayer(@PathVariable long id);
    public ResponseEntity<String> getAllPlayersAverage();
    public ResponseEntity<PlayerDto> getMostPercentLoser();
    public ResponseEntity<PlayerDto> getMostPercentWinner();



}
