package ItAcademy521.Sprint521.Model.Dto;
import ItAcademy521.Sprint521.Model.Entities.Game;
import ItAcademy521.Sprint521.Model.Entities.Player;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GameDto {

    private String playerName;
    private byte dice1;
    private byte dice2;
    private byte score;
    private Game.ResultGame resultGame;

    }


