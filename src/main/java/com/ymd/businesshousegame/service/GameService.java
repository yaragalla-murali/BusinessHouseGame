package com.ymd.businesshousegame.service;

import com.ymd.businesshousegame.entity.*;
import com.ymd.businesshousegame.exception.*;
import com.ymd.businesshousegame.repository.GameRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class GameService {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private GameRepository gameDao;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private CellService cellService;

    @Autowired
    private DiceService diceService;

    public Game createGame(Board board, Dice dice, Player player) {
        player.setPlayerPosition(1);
        player = playerService.savePlayer(player);
        Game game = new Game();
        game.setBoard(board);
        game.setDice(dice);
        List<Player> players = Arrays.asList(player);
        game.setPlayers(players);
        game.setNextPlayer(player);
        game.setStatus(GameStatus.CREATED);
        game = gameDao.save(game);
        gameDao.flush();
        return gameDao.getReferenceById(game.getId());
    }

    public Game addPlayerToGame(int gameId, Player player) {
        Game game = gameDao.findById(gameId).orElse(null);
        if (game == null) {
            throw new GameDoesNotExistException("Game with the gameId " + gameId + " does not exist.");
        }

        if (game.getStatus().equals(GameStatus.CREATED) && game.getPlayers().size() < 3) {
            List<Player> players = game.getPlayers();
            player.setPlayerPosition(players.size() + 1);
            player = playerService.savePlayer(player);
            players.add(player);
            game = gameDao.save(game);
            gameDao.flush();
        } else {
            throw new PlayerCannotBeAddedException("Player cannot be added at this stage.");
        }
        return game;
    }

    public Game movePlayer(int playerId, int gameId) {
        Game game = getGame(gameId);
        Player player = getPlayer(playerId, game);

        game.setStatus(GameStatus.INPROGRESS);
        Player nextPlayer = playerService.getNextPlayer(player, game);
        game.setNextPlayer(nextPlayer);
        if (player.getPlayerPosition() == 3) {
            game.setNumberOfTurnsCompleted(game.getNumberOfTurnsCompleted() + 1);
        }
        if (game.getNumberOfTurnsCompleted() == 10) {
            game.setStatus(GameStatus.COMPLETED);
        }
        playerService.savePlayer(player);

        return gameDao.save(game);
    }

    private Player getPlayer(int playerId, Game game) {
        Player player = playerService.getPlayer(playerId);
        if (player == null)
            throw new NoSuchPlayerException("No player exist with id: " + playerId);
        if (player.getId() != game.getNextPlayer().getId()) {
            throw new WrongPlayerException("Wrong Player. Not his turn.");
        }
        int playersCurrentPositionOnBoard = player.getCurrentPositionOnBoard() + diceService.giveDice(game.getDice());
        player.setCurrentPositionOnBoard(playersCurrentPositionOnBoard);
        player = cellService.handleCellLanding(game.getBoard(), player);

        return player;
    }

    private Game getGame(int gameId) {
        Game game = gameDao.findById(gameId).orElse(null);

        if (game == null) {
            throw new GameDoesNotExistException("Game with the gameId " + gameId + " does not exist.");
        }
        if (game.getStatus().equals(GameStatus.COMPLETED)) {
            throw new GameAlreadyCompletedException("The game with id " + gameId + " is already completed.");
        }
        return game;
    }

}
