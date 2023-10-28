package ItAcademy521.Sprint521.Model.Dto;

import ItAcademy521.Sprint521.Model.Entities.Game;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerDto {


    private ObjectId idPlayer;
    private String name;
    private final LocalDateTime registrationTime = LocalDateTime.now();
    private double winPercent;
    private double losePercent;
    private List<Game> gameList = new ArrayList<>();


}

