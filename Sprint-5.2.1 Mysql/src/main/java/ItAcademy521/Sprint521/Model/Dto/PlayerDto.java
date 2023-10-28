package ItAcademy521.Sprint521.Model.Dto;

import ItAcademy521.Sprint521.Model.Entities.Game;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerDto {


    private long idPlayer;
    private String name;
    private LocalDateTime registrationTime;
    private double winPercent;
    private double losePercent;
    private List<Game> gameList = new ArrayList<>();


}

