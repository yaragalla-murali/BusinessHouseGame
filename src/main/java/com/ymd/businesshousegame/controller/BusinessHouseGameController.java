package com.ymd.businesshousegame.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ymd.businesshousegame.entity.Board;
import com.ymd.businesshousegame.entity.Dice;
import com.ymd.businesshousegame.entity.Game;
import com.ymd.businesshousegame.entity.Player;
import com.ymd.businesshousegame.service.BoardService;
import com.ymd.businesshousegame.service.DiceService;
import com.ymd.businesshousegame.service.GameService;

@RestController
public class BusinessHouseGameController {

	@Autowired
	private BoardService boardService;
	@Autowired
	private GameService gameService;
	@Autowired
	private DiceService diceService;

	@PostMapping("/games")
	public Game createGame(@RequestBody Player player) {
		Board board = boardService.setupBoard(
				"E,E,J,H,E,T,J,T,E,E,H,J,T,H,E,E,J,H,E,T,J,T,E,E," + "H,J,T,H,J,E,E,J,H,E,T,J,T,E,E,H,J,T,E,H,E");
		Dice dice = diceService.setup("4,4,4,6,7,8,5,11,10,12,2,3,5,6,7,8,5,11,10,12,2," + "3,5,6,7,8,5,11,10,12");

		Game game = gameService.createGame(board, dice, player);
		return game;

	}

	@PutMapping("/games")
	public Game ConnectToGame(@RequestParam int gameId, @RequestBody Player player) {
		Game game = gameService.addPlayerToGame(gameId, player);
		return game;
	}

	@PutMapping("/games/players/{id}")
	public Game movePlayer(@PathVariable("id") int playerId, @RequestParam int gameId) {
		Game game = gameService.movePlayer(playerId, gameId);
		return game;
	}

}
