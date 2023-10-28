package ItAcademy521.Sprint521.Model.Services;

import ItAcademy521.Sprint521.Model.Dto.PlayerDto;
import ItAcademy521.Sprint521.Model.Entities.Player;
import org.bson.types.ObjectId;

import java.util.List;

public interface IPlayerService {


    Player createPlayer(Player player);
    Player updatePlayer(ObjectId id, Player player);
    void deleteGamesListPlayer(ObjectId Id);
    String getAllPlayersAverage();
    List<PlayerDto> playersWhoPlayed();
    PlayerDto getMostPercentLoser();
    PlayerDto getMostPercentWinner();


}

