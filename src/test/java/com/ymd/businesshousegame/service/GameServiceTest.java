package com.ymd.businesshousegame.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ymd.businesshousegame.entity.Board;
import com.ymd.businesshousegame.entity.Dice;
import com.ymd.businesshousegame.entity.Game;
import com.ymd.businesshousegame.entity.GameStatus;
import com.ymd.businesshousegame.entity.Player;
import com.ymd.businesshousegame.exception.GameAlreadyCompletedException;
import com.ymd.businesshousegame.exception.GameDoesNotExistException;
import com.ymd.businesshousegame.exception.NoSuchPlayerException;
import com.ymd.businesshousegame.exception.PlayerCannotBeAddedException;
import com.ymd.businesshousegame.exception.WrongPlayerException;
import com.ymd.businesshousegame.repository.GameRepository;

@ExtendWith(MockitoExtension.class)
public class GameServiceTest {

	@InjectMocks
	private GameService gameService;

	@Mock
	private PlayerService playerService;

	@Mock
	private GameRepository gameDao;

	@Mock
	private DiceService diceService;

	@Mock
	private CellService cellService;

	// private BoardService boardService;

	@Test
	void createGameTest() {

		Player player = new Player();
		player.setName("test player");

		Dice dice = new Dice();
		Board board = new Board();

		Player savedPlayer = new Player();
		savedPlayer.setId(10);
		savedPlayer.setName("Kiran");
		savedPlayer.setPlayerPosition(1);
		savedPlayer.setCurrentPositionOnBoard(1);

		Game savedGame = new Game();
		savedGame.setId(100);
		savedGame.setDice(dice);
		savedGame.setBoard(board);
		savedGame.setPlayers(List.of(savedPlayer));

		when(playerService.savePlayer(player)).thenReturn(savedPlayer);
		when(gameDao.save(any(Game.class))).thenReturn(savedGame);
		when(gameDao.getReferenceById(100)).thenReturn(savedGame);

		Game game = gameService.createGame(board, dice, player);
		assertEquals(100, game.getId());
	}

	@Test
	void testAddPlayerToGame() {
		Player player = new Player();
		player.setName("test player");

		Dice dice = new Dice();
		Board board = new Board();

		Player savedPlayer = new Player();
		savedPlayer.setId(11);
		savedPlayer.setName("Kiran");
		savedPlayer.setPlayerPosition(1);
		savedPlayer.setCurrentPositionOnBoard(1);

		Game savedGame = new Game();
		savedGame.setId(100);
		savedGame.setDice(dice);
		savedGame.setBoard(board);

		Player existingPlayer = new Player();
		existingPlayer.setId(1);
		existingPlayer.setName("Player 1");
		savedGame.getPlayers().add(existingPlayer);
		savedGame.setStatus(GameStatus.CREATED);

		when(gameDao.findById(100)).thenReturn(Optional.of(savedGame));
		when(playerService.savePlayer(player)).thenReturn(savedPlayer);
		when(gameDao.save(any(Game.class))).thenReturn(savedGame);

		Game game = gameService.addPlayerToGame(100, player);
		assertEquals(100, game.getId());
		assertEquals(11, game.getPlayers().get(1).getId());
	}

	@Test
	void testMovePlayer() {
		Dice dice = new Dice();
		dice.setLastUsedOutputIndex(2);
		Board board = new Board();

		Game savedGame = new Game();
		savedGame.setId(100);
		savedGame.setDice(dice);
		savedGame.setBoard(board);
		savedGame.setNumberOfTurnsCompleted(1);

		Player currentPlayer = new Player();
		currentPlayer.setId(1);
		currentPlayer.setName("Player 1");
		currentPlayer.setCurrentPositionOnBoard(3);
		currentPlayer.setPlayerPosition(1);
		currentPlayer.setTotalBalance(800);
		savedGame.getPlayers().add(currentPlayer);
		savedGame.setNextPlayer(currentPlayer);
		savedGame.setStatus(GameStatus.INPROGRESS);

		Player nextPlayer = new Player();
		nextPlayer.setId(2);

		when(gameDao.findById(100)).thenReturn(Optional.of(savedGame));
		when(playerService.getPlayer(1)).thenReturn(currentPlayer);
		when(diceService.giveDice(any())).thenReturn(3);
		when(playerService.getNextPlayer(currentPlayer, savedGame)).thenReturn(nextPlayer);
		when(playerService.savePlayer(currentPlayer)).thenReturn(currentPlayer);
		when(gameDao.save(savedGame)).thenReturn(savedGame);
		when(cellService.handleCellLanding(board, currentPlayer)).thenReturn(currentPlayer);

		Game result = gameService.movePlayer(1, 100);
		assertNotNull(result);
		assertEquals(800, result.getPlayers().getFirst().getTotalBalance());

	}

	@Test
	void testAddPlayerToGame_GameDoesNotExist() {
		int gameId = 1;
		Player player = new Player();

		when(gameDao.findById(gameId)).thenReturn(Optional.empty());

		GameDoesNotExistException exception = assertThrows(GameDoesNotExistException.class,
				() -> gameService.addPlayerToGame(gameId, player));

		assertEquals("Game with the gameId " + gameId + " does not exist.", exception.getMessage());

	}

	@Test
	void testAddPlayerToGame_PlayerCannotBeAdded() {

		int gameId = 1;
		Player player = new Player();

		List<Player> players = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			Player testPlayer = new Player();
			testPlayer.setId((i + 1));
			players.add(testPlayer);
		}
		Game game = new Game();
		game.setPlayers(players);
		game.setId(gameId);
		game.setStatus(GameStatus.CREATED);

		when(gameDao.findById(gameId)).thenReturn(Optional.of(game));

		PlayerCannotBeAddedException exception = assertThrows(PlayerCannotBeAddedException.class,
				() -> gameService.addPlayerToGame(gameId, player));

		assertEquals("Player cannot be added at this stage.", exception.getMessage());
	}

	@Test
	void movePlayerGameDoesNotExist() {
		int gameId = 10;
		int playerId = 4;
		when(gameDao.findById(gameId)).thenReturn(Optional.empty());

		GameDoesNotExistException exception = assertThrows(GameDoesNotExistException.class,
				() -> gameService.movePlayer(playerId, gameId));

		assertEquals("Game with the gameId " + gameId + " does not exist.", exception.getMessage());

	}

	@Test
	void movePlayerGameAlreadyCompleted() {
		int gameId = 10;
		int playerId = 3;
		Game game = new Game();
		game.setId(gameId);
		game.setStatus(GameStatus.COMPLETED);

		when(gameDao.findById(gameId)).thenReturn(Optional.of(game));

		GameAlreadyCompletedException exception = assertThrows(GameAlreadyCompletedException.class,
				() -> gameService.movePlayer(playerId, gameId));

		assertEquals("The game with id " + gameId + " is already completed.", exception.getMessage());
	}

	@Test
	void movePlayerNoSuchPlayer() {
		int gameId = 10;
		int playerId = 3;
		Game game = new Game();
		game.setId(gameId);
		game.setStatus(GameStatus.INPROGRESS);

		when(gameDao.findById(gameId)).thenReturn(Optional.of(game));
		when(playerService.getPlayer(anyInt())).thenReturn(null);

		NoSuchPlayerException exception = assertThrows(NoSuchPlayerException.class,
				() -> gameService.movePlayer(playerId, gameId));

		assertEquals("No player exist with id: " + playerId, exception.getMessage());
	}

	@Test
	void movePlayerWrongPlayer() {
		int gameId = 10;
		int playerId = 3;
		Game game = new Game();
		game.setId(gameId);
		game.setStatus(GameStatus.INPROGRESS);

		Player player = new Player();
		player.setId(playerId);

		Player nextPlayer = new Player();
		nextPlayer.setId(4);
		game.setNextPlayer(nextPlayer);

		when(gameDao.findById(gameId)).thenReturn(Optional.of(game));
		when(playerService.getPlayer(anyInt())).thenReturn(player);

		WrongPlayerException exception = assertThrows(WrongPlayerException.class,
				() -> gameService.movePlayer(playerId, gameId));

		assertEquals("Wrong Player. Not his turn.", exception.getMessage());
	}

	@Test
	void movePlayerNextPlayer() {
		int gameId = 10;
		int playerId = 3;
		Game game = new Game();
		game.setId(gameId);
		game.setStatus(GameStatus.INPROGRESS);
		game.setNumberOfTurnsCompleted(9);

		Player player = new Player();
		player.setId(playerId);
		player.setPlayerPosition(3);

		Player nextPlayer = new Player();
		nextPlayer.setId(3);
		game.setNextPlayer(nextPlayer);

		Dice dice = new Dice();
		dice.setId(1);
		dice.setLastUsedOutputIndex(1);
		game.setDice(dice);

		Board board = new Board();
		game.setBoard(board);

		when(gameDao.findById(anyInt())).thenReturn(Optional.of(game));
		when(playerService.getPlayer(anyInt())).thenReturn(player);
		when(diceService.giveDice(any(Dice.class))).thenReturn(3);
		when(cellService.handleCellLanding(any(Board.class), any(Player.class))).thenReturn(player);
		when(playerService.getNextPlayer(any(Player.class), any(Game.class))).thenReturn(nextPlayer);
		when(playerService.savePlayer(player)).thenReturn(player);
		when(gameDao.save(game)).thenReturn(game);

		Game resultGame = gameService.movePlayer(playerId, gameId);

		assertNotNull(resultGame);
		assertEquals(10, resultGame.getNumberOfTurnsCompleted());
		assertEquals(GameStatus.COMPLETED, game.getStatus());

		// assertEquals("Wrong Player. Not his turn.", exception.getMessage());
	}

}
