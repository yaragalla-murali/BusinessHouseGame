package com.ymd.businesshousegame.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ymd.businesshousegame.entity.Game;

@Repository
public interface GameRepository extends JpaRepository<Game, Integer> {

}
