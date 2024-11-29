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
        String[] boardCordsAsArray = boardCords.split(",");
        List<Cell> boardCells = new ArrayList<>();
        int counter = 0;
        for (String cord : boardCordsAsArray) {
            switch (cord) {
                case "J" -> {
                    Cell jail = new Cell();
                    jail.setCellType(CellType.JAIL);
                    jail.setSequenceOnBoard(counter);
                    boardCells.add(jail);
                }
                case "T" -> {
                    Cell treasure = new Cell();
                    treasure.setCellType(CellType.TREASURE);
                    treasure.setSequenceOnBoard(counter);
                    boardCells.add(treasure);
                }
                case "H" -> {
                    Cell hotel = new Cell();
                    hotel.setCellType(CellType.HOTEL);
                    hotel.setSequenceOnBoard(counter);
                    boardCells.add(hotel);
                }
                case "E" -> {
                    Cell empty = new Cell();
                    empty.setCellType(CellType.EMPTY);
                    empty.setSequenceOnBoard(counter);
                    boardCells.add(empty);
                }
            }
            counter = counter + 1;
        }
        Board board = new Board();
        boardCells = cellService.saveCells(boardCells);
        board.setBoardCells(boardCells);
        board = boardDao.save(board);
        boardDao.flush();
        return boardDao.getReferenceById(board.getId());

    }
}
