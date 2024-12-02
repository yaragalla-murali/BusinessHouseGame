package com.ymd.businesshousegame.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.ymd.businesshousegame.entity.Game;
import com.ymd.businesshousegame.entity.Player;
import com.ymd.businesshousegame.service.BoardService;
import com.ymd.businesshousegame.service.DiceService;
import com.ymd.businesshousegame.service.GameService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BusinessHouseGameController.class)
public class BusinessHouseControllerTest {

    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(), StandardCharsets.UTF_8);
    @Autowired
    private MockMvc mockMvc;
    @MockitoBean
    private GameService gameService;
    @MockitoBean
    private BoardService boardService;
    @MockitoBean
    private DiceService diceService;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreateGame() throws Exception {
        Player player = new Player("lakshman");
        Game game = new Game();

        given(gameService.createGame(any(), any(), any())).willReturn(game);

        ObjectWriter ow = getObjectWriter();
        String requestJson = ow.writeValueAsString(player);

        mockMvc.perform(post("/games").contentType(APPLICATION_JSON_UTF8).content(requestJson)
                        .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(game)));
    }

    @Test
    void testConnectToGame() throws Exception {
        Player player = new Player("lakshman");
        Game game = new Game();

        given(gameService.addPlayerToGame(anyInt(), any())).willReturn(game);

        ObjectWriter ow = getObjectWriter();
        String requestJson = ow.writeValueAsString(player);

        mockMvc.perform(put("/games?gameId=1").contentType(APPLICATION_JSON_UTF8).content(requestJson)
                        .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(game)));
    }

    @Test
    void movePlayer() throws Exception {
        Game game = new Game();
        given(gameService.movePlayer(anyInt(), anyInt())).willReturn(game);

        mockMvc.perform(put("/games/players/1?gameId=1").contentType(APPLICATION_JSON_UTF8).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(content().json(objectMapper.writeValueAsString(game)));
    }

    private static ObjectWriter getObjectWriter() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        return ow;
    }

}
