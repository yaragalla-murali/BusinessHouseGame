package com.ymd.businesshousegame.service;

import com.ymd.businesshousegame.entity.Game;
import com.ymd.businesshousegame.entity.Player;
import com.ymd.businesshousegame.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {
    @Autowired
    private PlayerRepository playerDao;

    public Player savePlayer(Player player) {
        return playerDao.save(player);
    }

    public Player getPlayer(int playerId) {
        return playerDao.findById(playerId).orElse(null);
    }

    public Player getNextPlayer(Player currentPlayer, Game game) {
        List<Player> players = game.getPlayers();
        return (currentPlayer.getPlayerPosition() == players.size()) ? players.getFirst() : players.get((currentPlayer.getPlayerPosition()));
    }
}
