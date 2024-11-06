package com.ymd.businesshousegame.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ymd.businesshousegame.entity.Player;

@Repository
public interface PlayerDao extends JpaRepository<Player, Integer> {

}
