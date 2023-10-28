package ItAcademy521.Sprint521.Model.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "games")
public class Game {

    public enum ResultGame{
        Winner,Loser
    }

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name="IdGame", length=50, nullable=false, unique=true)
        private long idGame;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "idPlayer")
        private Player player;

        @Column
        private byte dice1;
        @Column
        private byte dice2;
        @Column
        private byte score;
        @Column
        private LocalDateTime gameLocalTime;

        @Enumerated(EnumType.STRING)
        private ResultGame resultGame;

    public Game(Player player){

        this.player = player;
        this.dice1 = (byte) ((byte) Math.floor(Math.random() * 6) + 1);
        this.dice2 = (byte) ((byte) Math.floor(Math.random() * 6) + 1);
        this.score = (byte)(dice1 + dice2);
        this.resultGame = getResult(dice1,dice2);
        this.gameLocalTime = LocalDateTime.now();
    }

         public ResultGame getResult(byte dice1, byte dice2) {
            if (dice1 + dice2 > 7) {
                return ResultGame.Winner;
            } else {
                return ResultGame.Loser;
            }
        }

}
