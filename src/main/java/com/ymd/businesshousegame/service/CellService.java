package com.ymd.businesshousegame.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymd.businesshousegame.entity.Board;
import com.ymd.businesshousegame.entity.Cell;
import com.ymd.businesshousegame.entity.Player;
import com.ymd.businesshousegame.entity.CellType;
import com.ymd.businesshousegame.repository.CellRepository;

@Service
public class CellService {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private CellRepository cellDao;

	public List<Cell> saveCells(List<Cell> cells) {
		cells = cellDao.saveAll(cells);
		cellDao.flush();
		return cells;
	}

	public void handleCellLanding(Board board, Player player) {
		Cell cell = board.getBoardCells().get(player.getCurrentPositionOnBoard());
		CellType celltype = cell.getCellType();
		logger.info("****************Last used Index : " + celltype.toString());
		if (celltype.equals(CellType.JAIL)) {
			player.setTotalBalance(player.getTotalBalance() - cell.getJailPenalty());
		} else if (celltype.equals(CellType.TREASURE)) {
			player.setTotalBalance(player.getTotalBalance() + cell.getTreasureValue());
		} else if (celltype.equals(CellType.HOTEL) && cell.getHotelOwner() == null) {
			player.setTotalBalance(player.getTotalBalance() - cell.getHotelWorth());
			cell.setHotelOwner(player);
			cellDao.save(cell);
		} else if (celltype.equals(CellType.HOTEL) && cell.getHotelOwner() != null) {
			player.setTotalBalance(player.getTotalBalance() - cell.getHotelRent());
		}

	}
}
