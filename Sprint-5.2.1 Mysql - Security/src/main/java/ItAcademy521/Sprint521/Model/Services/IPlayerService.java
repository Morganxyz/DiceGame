package ItAcademy521.Sprint521.Model.Services;

import ItAcademy521.Sprint521.Model.Dto.PlayerDto;
import ItAcademy521.Sprint521.Model.Entities.Player;
import java.util.List;

public interface IPlayerService {


    Player createPlayer(Player player);
    Player updatePlayer(long id, Player player);
    void deleteGamesListPlayer(long Id);
    String getAllPlayersAverage();
    List<PlayerDto> playersWhoPlayed();
    PlayerDto getMostPercentLoser();
    PlayerDto getMostPercentWinner();

}

