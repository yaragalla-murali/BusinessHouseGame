package com.ymd.businesshousegame.service;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymd.businesshousegame.entity.Board;
import com.ymd.businesshousegame.entity.Dice;
import com.ymd.businesshousegame.entity.Game;
import com.ymd.businesshousegame.entity.Player;
import com.ymd.businesshousegame.exception.GameDoesNotExistException;
import com.ymd.businesshousegame.exception.PlayerCannotBeAddedException;
import com.ymd.businesshousegame.model.GameStatus;
import com.ymd.businesshousegame.repo.GameDao;

@Service
public class GameService {

	@Autowired
	private GameDao gameDao;

	@Autowired
	private PlayerService playerService;

	public Game createGame(Board board, Dice dice, Player player) {
		player.setPlayerPosition(1);
		player = playerService.createPlayer(player);
		Game game = new Game();
		game.setBoard(board);
		game.setDice(dice);
		List<Player> players = Arrays.asList(player);
		game.setPlayers(players);
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

		if (game.getStatus().equals(GameStatus.CREATED)) {
			List<Player> players = game.getPlayers();
			player.setPlayerPosition(players.size() + 1);
			player = playerService.createPlayer(player);
			players.add(player);
			game = gameDao.save(game);
			gameDao.flush();
		} else {
			throw new PlayerCannotBeAddedException("Player cannot be added at this stage.");
		}
		return game;
	}

}
