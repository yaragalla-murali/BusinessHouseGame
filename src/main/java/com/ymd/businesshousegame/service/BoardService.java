package com.ymd.businesshousegame.service;

import com.ymd.businesshousegame.entity.Board;
import com.ymd.businesshousegame.entity.Cell;
import com.ymd.businesshousegame.entity.CellType;
import com.ymd.businesshousegame.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardDao;

    @Autowired
    private CellService cellService;

    public Board setupBoard(String boardCords) {
        return boardDao.save(new Board(getBoardCells(boardCords.split(","))));
    }

    private static List<Cell> getBoardCells(String[] boardCordsAsArray) {
        List<Cell> boardCells = new ArrayList<>();
        int counter = 0;
        for (String cord : boardCordsAsArray) {
            switch (cord) {
                case "J" -> boardCells.add(new Cell(CellType.JAIL, counter));
                case "T" -> boardCells.add(new Cell(CellType.TREASURE, counter));
                case "H" -> boardCells.add(new Cell(CellType.HOTEL, counter));
                case "E" -> boardCells.add(new Cell(CellType.EMPTY, counter));
            }
            counter = counter + 1;
        }
        return boardCells;
    }
}
