package ItAcademy521.Sprint521.Model.Services;

import ItAcademy521.Sprint521.Model.Dto.GameDto;
import ItAcademy521.Sprint521.Model.Entities.Game;

import java.util.List;

public interface IGameService {

   GameDto createGameDto(long id);
   List<GameDto> listPlayerGames(long id);
}


