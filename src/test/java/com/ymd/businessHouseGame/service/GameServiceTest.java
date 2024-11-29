package com.ymd.businessHouseGame.service;

import com.ymd.businesshousegame.entity.*;
import com.ymd.businesshousegame.repository.GameRepository;
import com.ymd.businesshousegame.service.CellService;
import com.ymd.businesshousegame.service.DiceService;
import com.ymd.businesshousegame.service.GameService;
import com.ymd.businesshousegame.service.PlayerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

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


    //private BoardService boardService;

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
        savedGame.setPlayers(Arrays.asList(savedPlayer));

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
        assertEquals(800, result.getPlayers().get(0).getTotalBalance());
    }
}
