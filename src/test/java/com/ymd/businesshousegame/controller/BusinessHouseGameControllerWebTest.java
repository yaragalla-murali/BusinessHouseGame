package com.ymd.businesshousegame.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import com.ymd.businesshousegame.entity.Board;
import com.ymd.businesshousegame.entity.Game;
import com.ymd.businesshousegame.entity.GameStatus;
import com.ymd.businesshousegame.entity.Player;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
public class BusinessHouseGameControllerWebTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	void testCreateGame() {
		Player player = new Player();
		player.setName("Player1");

	//	Board board = boardService.setupBoard(
      //          "E,E,J,H,E,T,J,T,E,E,H,J,T,H,E,E,J,H,E,T,J,T,E,E," + "H,J,T,H,J,E,E,J,H,E,T,J,T,E,E,H,J,T,E,H,E");
	//	Game expectedGame = new Game(); 
	//	expectedGame.

        Game actualGame = restTemplate.postForEntity("/games", player, Game.class).getBody();

		//assertEquals(expectedGame, actualGame);

	}

}
