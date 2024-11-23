package com.ymd.businesshousegame.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ymd.businesshousegame.entity.Player;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer> {

}
