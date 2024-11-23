package com.ymd.businesshousegame.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ymd.businesshousegame.entity.Game;

@Repository
public interface GameRepository extends JpaRepository<Game, Integer> {

}
