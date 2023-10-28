package ItAcademy521.Sprint521.Model.Entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "players")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idPlayer")
    private long idPlayer;
    @Column(name="name")
    private String name;
    @Column(name="localTime")
    private final LocalDateTime registrationTime = LocalDateTime.now();

    @OneToMany(mappedBy = "player", fetch = FetchType.EAGER, orphanRemoval = true, targetEntity = Game.class, cascade = CascadeType.ALL)
    private List<Game>gameList = new ArrayList<>();

}
