package com.ymd.businesshousegame.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymd.businesshousegame.entity.Game;
import com.ymd.businesshousegame.entity.Player;
import com.ymd.businesshousegame.repo.PlayerRepository;

@Service
public class PlayerService {
	@Autowired
	private PlayerRepository playerDao;

	public Player savePlayer(Player player) {
		player = playerDao.save(player);
		playerDao.flush();
		return playerDao.getReferenceById(player.getId());
	}

	public Player getPlayer(int playerId) {
		Player player = playerDao.findById(playerId).orElse(null);
		return player;
	}

	public Player getNextPlayer(Player currentPlayer, Game game) {
		Player nextPlayer = null;
		if (currentPlayer.getPlayerPosition() == game.getPlayers().size())
			nextPlayer = game.getPlayers().get(0);
		else
			nextPlayer = game.getPlayers().get((currentPlayer.getPlayerPosition()));
		return nextPlayer;
	}
}
