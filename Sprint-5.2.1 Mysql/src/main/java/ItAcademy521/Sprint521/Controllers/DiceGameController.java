package ItAcademy521.Sprint521.Controllers;

import ItAcademy521.Sprint521.Model.Dto.GameDto;
import ItAcademy521.Sprint521.Model.Dto.PlayerDto;
import ItAcademy521.Sprint521.Model.Entities.Player;
import ItAcademy521.Sprint521.Model.Exceptions.GameListIsEmptyException;
import ItAcademy521.Sprint521.Model.Exceptions.PlayerListIsEmptyException;
import ItAcademy521.Sprint521.Model.Exceptions.PlayerNotFoundException;
import ItAcademy521.Sprint521.Model.Exceptions.SomeErrorOcurredWhileCreatingException;
import ItAcademy521.Sprint521.Model.Services.DiceGameServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DiceGameController implements IDiceGameController {

    @Autowired
    private DiceGameServiceImp diceGameServiceImp;

    @PostMapping("/players/add")
    public ResponseEntity<Player> addPlayer(@RequestBody Player player){

        try {
            Player createdPlayer = diceGameServiceImp.createPlayer(player);
            return new ResponseEntity<>(createdPlayer, HttpStatus.CREATED);
        }catch(SomeErrorOcurredWhileCreatingException ex){
            throw ex;
        }

    }

    @PutMapping("/players/update/{id}")
    public ResponseEntity<Player> updatePlayer(@PathVariable long id, @RequestBody Player player){
        ;
        try{
            Player updatedPlayer = diceGameServiceImp.updatePlayer(id,player);
            return new ResponseEntity<>(updatedPlayer,HttpStatus.OK);
        }catch(SomeErrorOcurredWhileCreatingException ex){
          throw ex;
        }
    }
    @DeleteMapping("/players/{id}/games")
    public ResponseEntity<?> deleteGamesListPlayer(@PathVariable long id) {

        try{
            diceGameServiceImp.deleteGamesListPlayer(id);
            return new ResponseEntity<>(null,HttpStatus.OK);
        }catch(GameListIsEmptyException ex){
            throw ex;
        }
    }

    @GetMapping("/players")
    public ResponseEntity<List<PlayerDto>> getAllPlayersWithAveragePercent(){

            try{
                List<PlayerDto > listPlayerDto = diceGameServiceImp.playersWhoPlayed();
                return new ResponseEntity<>(listPlayerDto,HttpStatus.OK);
            }catch(PlayerListIsEmptyException ex){
            throw ex;
        }
    }

    @Override
    @PostMapping("/players/{id}/games")
    public ResponseEntity<GameDto> newGame(@PathVariable long id) {

        try {
            return new ResponseEntity<>(diceGameServiceImp.createGameDto(id), HttpStatus.CREATED);
        } catch (PlayerNotFoundException e) {
            throw e;
        } catch (SomeErrorOcurredWhileCreatingException ex) {
            throw ex;
        }
    }

    @Override
    @GetMapping("/players/{id}/games")
    public ResponseEntity<List<GameDto>> listGamesPlayer(@PathVariable long id) {

        try {
            return new ResponseEntity<>(diceGameServiceImp.listPlayerGames(id), HttpStatus.OK);
        } catch (PlayerListIsEmptyException ex) {
            throw ex;
        } catch (PlayerNotFoundException ex1) {
            throw ex1;
        }
    }

    @Override
    @GetMapping("/players/ranking")
    public ResponseEntity<String> getAllPlayersAverage() {

        try {
            return new ResponseEntity<>(diceGameServiceImp.getAllPlayersAverage(), HttpStatus.OK);
        } catch (PlayerListIsEmptyException e) {
            throw e;
        }
    }

    @Override
    @GetMapping("/players/ranking/loser")
    public ResponseEntity<PlayerDto> getMostPercentLoser() {

        try {
            return new ResponseEntity<>(diceGameServiceImp.getMostPercentLoser(), HttpStatus.OK);
        } catch (GameListIsEmptyException ex) {
            throw ex;
        } catch (PlayerListIsEmptyException ex1) {
            throw ex1;
        }
    }

    @Override
    @GetMapping("/players/ranking/winner")
    public ResponseEntity<PlayerDto> getMostPercentWinner() {

        try {
            return new ResponseEntity<>(diceGameServiceImp.getMostPercentWinner(), HttpStatus.OK);
        } catch (GameListIsEmptyException ex) {
            throw ex;
        } catch (PlayerListIsEmptyException ex1) {
            throw ex1;
        }
    }

    @DeleteMapping("/players/deleteAllPlayers")
    public ResponseEntity<?> deleTeAllPlayers(){

        try{
            diceGameServiceImp.deleteAllPlayers();
            return new ResponseEntity<>("All players have been succesfully deleted",HttpStatus.OK);
        }catch(PlayerListIsEmptyException ex){
            throw ex;
        }
    }
}
