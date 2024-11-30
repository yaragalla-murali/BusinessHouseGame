package com.ymd.businessHouseGame.service;

import com.ymd.businesshousegame.entity.Board;
import com.ymd.businesshousegame.entity.Cell;
import com.ymd.businesshousegame.entity.CellType;
import com.ymd.businesshousegame.repository.BoardRepository;
import com.ymd.businesshousegame.service.BoardService;
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
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BoardServiceTest {

	@InjectMocks
	private BoardService boardService;

	@Mock
	private BoardRepository boardDao;

	@Mock
	private CellService cellService;

	@Test
	void testSetupBoard() {
		String boardCords = "E,E,J,H,E,T,J,T,E,E,H,J,T,H,E,E,J,H,E,T,J,T,E,E,H,J,T,E,H,E";
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

		when(cellService.saveCells(anyList())).thenReturn(boardCells);
		when(boardDao.save(any(Board.class))).thenReturn(board);
		when(boardDao.getReferenceById(anyInt())).thenReturn(board);

		Board result = boardService.setupBoard(boardCords);

		assertNotNull(result);
		assertEquals(boardCells.size(), result.getBoardCells().size());
		for (int i = 0; i < result.getBoardCells().size(); i++) {
			assertEquals(boardCells.get(i).getCellType(), result.getBoardCells().get(i).getCellType());
		}

	}
}
