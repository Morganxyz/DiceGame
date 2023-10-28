package ItAcademy521.Sprint521.Model.Entities;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Game {

    public enum ResultGame{
        Winner,Loser
    }
        private byte dice1;
        private byte dice2;
        private byte score;
        private ResultGame resultGame;

    public Game(){

        this.dice1 = (byte) ((byte) Math.floor(Math.random() * 6) + 1);
        this.dice2 = (byte) ((byte) Math.floor(Math.random() * 6) + 1);
        this.score = (byte)(dice1 + dice2);
        this.resultGame = getResult(dice1, dice2);

    }

    public ResultGame getResult(byte dice1, byte dice2) {
        if (dice1 + dice2 > 7) {
            return ResultGame.Winner;
        } else {
            return ResultGame.Loser;
        }
    }
    }

