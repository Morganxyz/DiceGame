package ItAcademy521.Sprint521.Model.Services;
import ItAcademy521.Sprint521.Model.Dto.GameDto;
import ItAcademy521.Sprint521.Model.Dto.PlayerDto;
import ItAcademy521.Sprint521.Model.Entities.Game;
import ItAcademy521.Sprint521.Model.Entities.Player;
import ItAcademy521.Sprint521.Model.Exceptions.GameListIsEmptyException;
import ItAcademy521.Sprint521.Model.Exceptions.PlayerListIsEmptyException;
import ItAcademy521.Sprint521.Model.Exceptions.PlayerNameMustBeUniqueException;
import ItAcademy521.Sprint521.Model.Exceptions.PlayerNotFoundException;
import ItAcademy521.Sprint521.Model.Repository.GameRepository;
import ItAcademy521.Sprint521.Model.Repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DiceGameServiceImp implements IPlayerService, IGameService{


    private final PlayerRepository playerRepository;
    private final GameRepository  gameRepository;

    @Autowired
    public DiceGameServiceImp(PlayerRepository playerRepository,GameRepository gameRepository){
        this.playerRepository = playerRepository;
        this.gameRepository = gameRepository;
    }

    public PlayerDto playertoDto(Player player){

        PlayerDto playerDto = new PlayerDto();
        playerDto.setIdPlayer(player.getIdPlayer());
        playerDto.setName(player.getName());
        playerDto.setRegistrationTime(player.getRegistrationTime());

        return playerDto;
    }

    public GameDto gameToDto(Game game){

        GameDto gameDto = new GameDto();
        gameDto.setIdGame(game.getIdGame());
        gameDto.setPlayerId(game.getPlayer().getIdPlayer());
        gameDto.setPlayerName(game.getPlayer().getName());
        gameDto.setDice1(game.getDice1());
        gameDto.setDice2(game.getDice2());
        gameDto.setScore(game.getScore());
        gameDto.setResultGame(game.getResultGame());

        return gameDto;

    }
    @Override
    public Player createPlayer(Player player) {

        Player playerAnonymous = anonymous(player);
        Player playerValidated = validatePlayerName(playerAnonymous);
        Player playerCreated = playerRepository.save(playerValidated);

        return playerCreated;
    }

    public Player anonymous(Player player) {
        if ((player == null)||(player.getName().equals("") || (player.getName().equals(" ")|| (player.getName().isEmpty()) || (player.getName().isBlank())))) {
            player.setName("Anonymous");
        }
        return player;
    }
    public Player validatePlayerName(Player player) {

        boolean esc = false;
        int i = 0;

        List<PlayerDto> playerListDto = getAllPlayers();

        while (i < playerListDto.size() && !esc) {
            PlayerDto playerDtoFound = getAllPlayers().get(i);
            if (playerDtoFound.getName().equalsIgnoreCase(player.getName()) && !player.getName().equalsIgnoreCase( "Anonymous")) {
                esc = true;
                throw new PlayerNameMustBeUniqueException("Player's name must be unique");
            }
            i++;
        }
        return player;
    }

    @Override
    public Player updatePlayer(long id, Player player) {

        Player playerFound = getPlayerById(id);

        Player playerValidated = validatePlayerName(player);

            playerFound.setName(playerValidated.getName());
            playerRepository.save(playerFound);

            return playerFound;

    }

    @Override
    @Transactional
    public void deleteGamesListPlayer(long id) {

        Optional<Player> playerFound = playerRepository.findById(id);

        if(playerFound.isPresent()){
        Player playerGamesToDelete = playerFound.get();
         if(!playerGamesToDelete.getGameList().isEmpty()) {
             playerGamesToDelete.getGameList().removeIf(game -> true);
         }else{
             throw new GameListIsEmptyException("GameList is empty!");
         }
        }else{
            throw new PlayerNotFoundException("Player Id not found");
        }
    }
    public Player getPlayerById(long id) {

        Optional<Player> playerFound = playerRepository.findById(id);

        if(playerFound.isPresent()){
            return playerFound.get();
        }else{
            throw new PlayerNotFoundException("Not player found whit this id");
        }

    }
    @Override
    public PlayerDto getMostPercentLoser() {

        List<PlayerDto> playersWhoPlayed = playersWhoPlayed();

        Optional<PlayerDto> mostPercentLoser = playersWhoPlayed.stream()
                .max(Comparator.comparingDouble(PlayerDto::getLosePercent));

        return mostPercentLoser.orElse(null);
    }

    @Override
    public PlayerDto getMostPercentWinner() {

        List<PlayerDto> playersWhoPlayed = playersWhoPlayed();

        Optional<PlayerDto> mostPercentWinner = playersWhoPlayed.stream()
                .max(Comparator.comparingDouble(PlayerDto::getWinPercent));

        return mostPercentWinner.orElse(null);
    }

    public List<PlayerDto> getAllPlayers(){

        List<Player> listPlayers = playerRepository.findAll();

        List<PlayerDto>listPlayersDto = listPlayers
                .stream().map(this::playertoDto).collect(Collectors.toList());

        return listPlayersDto;
    }

    public Player ifExistsPlayer(Player player) {

        if (playerRepository.existsById(player.getIdPlayer())) {
            return player;
        } else {
            throw new PlayerNotFoundException("Player not found");
        }
    }
    public PlayerDto getPlayerWithPercent(Player player) {

        Player playerFound = ifExistsPlayer(player);
        PlayerDto playerDTO = playertoDto(playerFound);

        if (playerFound.getGameList().isEmpty()) {
            playerDTO.setWinPercent(0d);
            playerDTO.setLosePercent(0d);
        } else{

            long totalGames = playerFound.getGameList().size();

            long totalWins = playerFound.getGameList().stream().filter(g -> g.getResult(g.getDice1(), g.getDice2()) == Game.ResultGame.Winner).count();
            double winPercent = ((double) totalWins / totalGames) * 100d;

            long totalLose = playerFound.getGameList().stream().filter(g -> g.getResult(g.getDice1(), g.getDice2()) == Game.ResultGame.Loser).count();
            double losePercent = ((double) totalLose / totalGames) * 100d;

            playerDTO.setWinPercent(Math.round(winPercent));;
            playerDTO.setLosePercent(Math.round(losePercent));
        }
        return playerDTO;
    }
    public String getAllPlayersAverage() {

        List<PlayerDto> playersWhoPlayed = playersWhoPlayed();

        double totalWinPercent = playersWhoPlayed.stream()
                .mapToDouble(PlayerDto::getWinPercent)
                .sum();

        double totalLosePercent = playersWhoPlayed.stream()
                .mapToDouble(PlayerDto::getLosePercent)
                .sum();

        double winAverage = totalWinPercent / playersWhoPlayed.size();
        double loseAverage = totalLosePercent / playersWhoPlayed.size();

        return "Win total average: " + winAverage + " // Lose total average: " + loseAverage;
    }


    public List<PlayerDto> playersWhoPlayed() {

        List<Player> allPlayers = playerRepository.findAll();

        List<PlayerDto> allPlayersDto= new ArrayList<>();

        allPlayers.forEach(p -> {

            PlayerDto playerDto = getPlayerWithPercent(p);

            if (playerDto.getWinPercent() >= 0d || (playerDto.getLosePercent() >= 0d));{
                allPlayersDto.add(playerDto);
            }

            if(allPlayersDto.isEmpty()){
                throw new GameListIsEmptyException("Game list is Empty! ");
            }
        });

        return allPlayersDto;
    }

    @Override
    public GameDto createGameDto(long id) {

        Player playerFound = getPlayerById(id);

        Game gameCreated = new Game(playerFound);

        playerFound.getGameList().add(gameCreated);

        gameRepository.save(gameCreated);

        return gameToDto(gameCreated);
    }

    public List<Game> ifExistsGames(long id) {

        Player playerFound = getPlayerById(id);
        if (playerFound.getGameList().isEmpty()) {
            throw new PlayerListIsEmptyException("There are no games played by " + playerFound.getName());
        } else {
            return playerFound.getGameList();
        }
    }

    @Override
    public List<GameDto> listPlayerGames(long id) {

        List<Game> gameList = ifExistsGames(id);

        List<GameDto> listGameDto = gameList
                .stream()
                .map(this::gameToDto)
                .collect(Collectors.toList());

        return listGameDto;
    }

    public void deleteAllPlayers(){

       ;List<Player>listPlayerToDelete = playerRepository.findAll();

        if(!listPlayerToDelete.isEmpty()){
            playerRepository.deleteAll(listPlayerToDelete);
        }else{
            throw new PlayerListIsEmptyException("Player List is empty");
        }

    }

}
