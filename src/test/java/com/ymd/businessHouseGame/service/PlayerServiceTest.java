package com.ymd.businessHouseGame.service;

import com.ymd.businesshousegame.entity.Game;
import com.ymd.businesshousegame.entity.Player;
import com.ymd.businesshousegame.repository.PlayerRepository;
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
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PlayerServiceTest {

    @InjectMocks
    private PlayerService playerService;

    @Mock
    private PlayerRepository playerDao;

    @Test
    void testSavePlayer() {
        Player player = new Player();
        player.setId(10);
        player.setName("test player");

        when(playerDao.save(any(Player.class))).thenReturn(player);
        when(playerDao.getReferenceById(anyInt())).thenReturn(player);

        Player resultPlayer = playerService.savePlayer(player);

        assertNotNull(resultPlayer);
        assertEquals(10, resultPlayer.getId());
    }

    @Test
    void testGetPlayer() {

        Player player = new Player();
        player.setId(10);
        player.setName("test player");

        when(playerDao.findById(anyInt())).thenReturn(Optional.of(player));

        Player result = playerService.getPlayer(10);

        assertNotNull(result);
        assertEquals(10, result.getId());
    }

    @Test
    void testGetNextPlayer() {
        Game game = new Game();
        game.setId(10);

        Player player1 = new Player();
        player1.setId(11);
        player1.setName("test player");
        player1.setPlayerPosition(1);

        Player player2 = new Player();
        player2.setId(12);
        player2.setName("test player2");

        game.setPlayers(Arrays.asList(player1, player2));

        Player resultPlayer = playerService.getNextPlayer(player1, game);
        assertNotNull(resultPlayer);
        assertEquals(12, resultPlayer.getId());
    }

    @Test
    void testGetNextPlayer_nextTurn() {
        Game game = new Game();
        game.setId(10);

        Player player1 = new Player();
        player1.setId(11);
        player1.setName("test player");
        player1.setPlayerPosition(1);

        Player player2 = new Player();
        player2.setId(12);
        player2.setName("test player2");
        player2.setPlayerPosition(2);

        game.setPlayers(Arrays.asList(player1, player2));

        Player resultPlayer = playerService.getNextPlayer(player2, game);
        assertNotNull(resultPlayer);
        assertEquals(11, resultPlayer.getId());
    }

}
