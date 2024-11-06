package com.ymd.businesshousegame.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymd.businesshousegame.entity.Player;
import com.ymd.businesshousegame.repo.PlayerDao;

@Service
public class PlayerService {
	@Autowired
	private PlayerDao playerDao;

	public Player createPlayer(Player player) {
		player=playerDao.save(player);
		playerDao.flush();
		return playerDao.getReferenceById(player.getId());
	}
}
