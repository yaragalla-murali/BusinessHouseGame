package com.ymd.businesshousegame.service;

import static com.ymd.businesshousegame.entity.CellType.EMPTY;
import static com.ymd.businesshousegame.entity.CellType.HOTEL;
import static com.ymd.businesshousegame.entity.CellType.JAIL;
import static com.ymd.businesshousegame.entity.CellType.TREASURE;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymd.businesshousegame.entity.Board;
import com.ymd.businesshousegame.entity.Cell;
import com.ymd.businesshousegame.repository.BoardRepository;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardDao;

    public Board setupBoard(String boardCords) {
        return boardDao.save(new Board(getBoardCells(boardCords.split(","))));
    }

    private static List<Cell> getBoardCells(String[] boardCordsAsArray) {
        List<Cell> boardCells = new ArrayList<>();
        int counter = 0;
        for (String cord : boardCordsAsArray) {
            switch (cord) {
                case "J" -> boardCells.add(new Cell(JAIL, counter++));
                case "T" -> boardCells.add(new Cell(TREASURE, counter++));
                case "H" -> boardCells.add(new Cell(HOTEL, counter++));
                case "E" -> boardCells.add(new Cell(EMPTY, counter++));
                default -> throw new RuntimeException("Invalid Cell Type");
            }
        }
        return boardCells;
    }
}
