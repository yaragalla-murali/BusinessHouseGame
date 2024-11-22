package com.ymd.businessHouseGame;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.ymd.businesshousegame.entity.Board;
import com.ymd.businesshousegame.entity.Dice;
import com.ymd.businesshousegame.entity.Game;
import com.ymd.businesshousegame.entity.Player;
import com.ymd.businesshousegame.service.BoardService;
import com.ymd.businesshousegame.service.DiceService;
import com.ymd.businesshousegame.service.GameService;

@SpringBootTest 
@AutoConfigureMockMvc
//@WebMvcTest(BusinessHouseGameController.class)
public class BusinessHouseControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private GameService gameService;

	@MockBean
	private BoardService boardService;

	@MockBean
	private DiceService diceService;
	
	@Autowired
    private ObjectMapper objectMapper;
	
	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
	
	
	@Test
	void testCreateGame()throws Exception {

		Board board = boardService.setupBoard(
				"E,E,J,H,E,T,J,T,E,E,H,J,T,H,E,E,J,H,E,T,J,T,E,E," + "H,J,T,H,J,E,E,J,H,E,T,J,T,E,E,H,J,T,E,H,E");
		Dice dice = diceService.setup("4,4,4,6,7,8,5,11,10,12,2,3,5,6,7,8,5,11,10,12,2," + "3,5,6,7,8,5,11,10,12");
		Player player = new Player();
		player.setName("lakshman");
		Game game = new Game();

		given(gameService.createGame(board, dice, player)).willReturn(game);
		
		ObjectMapper mapper = new ObjectMapper();
	    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
	    ObjectWriter ow = mapper.writer();//.withDefaultPrettyPrinter();
	    String requestJson=ow.writeValueAsString(player );

		mockMvc
	      .perform(post("/games")
	      .contentType(APPLICATION_JSON_UTF8)
	      .content(requestJson)
	      .accept(MediaType.APPLICATION_JSON))
	      .andExpect(status().isOk())
	      .andExpect(content().json(objectMapper.writeValueAsString(game)));
	}

}
