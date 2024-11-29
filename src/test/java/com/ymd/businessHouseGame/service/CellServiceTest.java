package com.ymd.businessHouseGame.service;

import com.ymd.businesshousegame.entity.Board;
import com.ymd.businesshousegame.entity.Cell;
import com.ymd.businesshousegame.entity.CellType;
import com.ymd.businesshousegame.entity.Player;
import com.ymd.businesshousegame.repository.CellRepository;
import com.ymd.businesshousegame.service.CellService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CellServiceTest {

    @Mock
    private CellRepository cellDao;

    @InjectMocks
    private CellService cellService;

    @Test
    void testSaveCells() {
        List<Cell> cellList = new ArrayList<>();

        Cell cell1 = new Cell();
        cell1.setCellType(CellType.JAIL);
        cell1.setId(1);
        cellList.add(cell1);

        Cell cell2 = new Cell();
        cell2.setCellType(CellType.TREASURE);
        cell2.setId(2);
        cellList.add(cell2);

        when(cellDao.saveAll(anyList())).thenReturn(cellList);

        List<Cell> cells = cellService.saveCells(cellList);
        assertNotNull(cells);
        for (int i = 0; i < cellList.size(); i++) {
            assertEquals(cellList.get(i).getId(), cells.get(i).getId());
            assertEquals(cellList.get(i).getCellType(), cells.get(i).getCellType());
        }

    }

    @Test
    void testHandleCellLanding() {

        String boardCords = "T,E,J,H,E,T,J,T,E,E,H,J,T,H,E,E,J,H,E,T,J,T,E,E,H,J,T,E,H,E";
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
        board.setBoardCells(boardCells);
        board.setId(1);

        Cell cell = new Cell();
        cell.setCellType(CellType.JAIL);
        cell.setId(1);

        Player player = new Player();
        player.setId(1);
        player.setName("Test Player");

        Player resultPlayer = cellService.handleCellLanding(board, player);

        assertNotNull(resultPlayer);
        assertEquals(1200, resultPlayer.getTotalBalance());

    }
}
