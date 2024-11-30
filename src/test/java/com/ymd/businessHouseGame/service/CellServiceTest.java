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
        List<Cell> cellList = List.of(new Cell(1, CellType.JAIL), new Cell(2, CellType.TREASURE));

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
                case "J" -> boardCells.add(new Cell(CellType.JAIL, counter));
                case "T" -> boardCells.add(new Cell(CellType.TREASURE, counter));
                case "H" -> boardCells.add(new Cell(CellType.HOTEL, counter));
                case "E" -> boardCells.add(new Cell(CellType.EMPTY, counter));
            }
            counter = counter + 1;
        }

        Board board = new Board(1, boardCells);

        Player player = new Player(1, "Test Player");

        Player resultPlayer = cellService.handleCellLanding(board, player);

        assertNotNull(resultPlayer);
        assertEquals(1200, resultPlayer.getTotalBalance());

    }
}
