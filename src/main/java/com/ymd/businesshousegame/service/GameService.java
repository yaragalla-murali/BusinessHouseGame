package com.ymd.businesshousegame.service;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymd.businesshousegame.entity.Board;
import com.ymd.businesshousegame.entity.Dice;
import com.ymd.businesshousegame.entity.DiceOutput;
import com.ymd.businesshousegame.entity.Game;
import com.ymd.businesshousegame.entity.Player;
import com.ymd.businesshousegame.exception.GameAlreadyCompletedException;
import com.ymd.businesshousegame.exception.GameDoesNotExistException;
import com.ymd.businesshousegame.exception.NoSuchPlayerException;
import com.ymd.businesshousegame.exception.PlayerCannotBeAddedException;
import com.ymd.businesshousegame.exception.WrongPlayerException;
import com.ymd.businesshousegame.model.GameStatus;
import com.ymd.businesshousegame.repo.GameDao;

@Service
public class GameService {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private GameDao gameDao;

	@Autowired
	private PlayerService playerService;

	@Autowired
	private DiceOutputService diceOutputService;

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

		if (game.getStatus().equals(GameStatus.CREATED) && game.getPlayers().size() <= 3) {
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
		Game game = gameDao.findById(gameId).orElse(null);

		if (game == null) {
			throw new GameDoesNotExistException("Game with the gameId " + gameId + " does not exist.");
		}
		if (game.getStatus().equals(GameStatus.COMPLETED)) {
			throw new GameAlreadyCompletedException("The game with id " + gameId + " is already completed.");
		}
		Player player = playerService.getPlayer(playerId);
		if (player == null)
			throw new NoSuchPlayerException("No player exist with id: " + playerId);
		if (player.getId() != game.getNextPlayer().getId()) {
			throw new WrongPlayerException("Wrong Player. Not his turn.");
		}

		int lastUsedIndex = game.getDice().getLastUsedOutputIndex();
		logger.info("****************Last used Index : " + lastUsedIndex);
		DiceOutput output = diceOutputService.giveDice(game.getDice(), (lastUsedIndex + 1));
		logger.info("****************Dice output : " + output.getOutput());
		int playersCurrentPostionOnBoard = player.getCurrentPositionOnBoard() + output.getOutput();
		logger.info("****************Players current Position On Board : " + playersCurrentPostionOnBoard);
		player.setCurrentPositionOnBoard(playersCurrentPostionOnBoard);
		cellService.handleCellLanding(game.getBoard(), player);
		logger.info("****************Player Total Balance : " + player.getTotalBalance());
		game.setStatus(GameStatus.INPROGRESS);
		Player nextPlayer = playerService.getNextPlayer(player, game);
		game.setNextPlayer(nextPlayer);
		if (player.getPlayerPosition() == 3)
			game.setNumberOfTurnsCompleted(game.getNumberOfTurnsCompleted() + 1);
		if (game.getNumberOfTurnsCompleted() == 10)
			game.setStatus(GameStatus.COMPLETED);

		Dice dice = game.getDice();
		dice.setLastUsedOutputIndex(lastUsedIndex + 1);
		diceService.saveDice(dice);
		playerService.savePlayer(player);
		game.setDice(dice);
		game = gameDao.save(game);

		return game;
	}

}
