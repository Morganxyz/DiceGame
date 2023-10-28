package ItAcademy521.Sprint521.Model.Services;

import ItAcademy521.Sprint521.Model.Dto.GameDto;
import ItAcademy521.Sprint521.Model.Entities.Game;
import org.bson.types.ObjectId;

import java.util.List;

public interface IGameService {

   GameDto createGameDto(ObjectId id);
   List<GameDto> listPlayerGames(ObjectId id);
}


