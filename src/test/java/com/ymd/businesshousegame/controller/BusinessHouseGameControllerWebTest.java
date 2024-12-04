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

		ResponseEntity<Game> response = restTemplate.postForEntity("/games", player, Game.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());

		Game createdGame = response.getBody();

		assertNotNull(createdGame);
		assertNotNull(createdGame.getId());
		assertNotNull(createdGame.getBoard());
		assertNotNull(createdGame.getDice());
		assertEquals(GameStatus.CREATED, createdGame.getStatus());
		assertEquals("Player1", createdGame.getPlayers().get(0).getName());

	}

}
