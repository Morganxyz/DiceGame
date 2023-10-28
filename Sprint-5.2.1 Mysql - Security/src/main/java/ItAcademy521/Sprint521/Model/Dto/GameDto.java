package ItAcademy521.Sprint521.Model.Dto;
import ItAcademy521.Sprint521.Model.Entities.Game;
import ItAcademy521.Sprint521.Model.Entities.Player;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GameDto {
    public enum ResultGame{
        Winner,Loser
    }

    private long idGame;
    private long playerId;
    private String playerName;
    private byte dice1;
    private byte dice2;
    private byte score;
    private Game.ResultGame resultGame;

}
