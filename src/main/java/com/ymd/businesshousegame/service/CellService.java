package com.ymd.businesshousegame.service;

import com.ymd.businesshousegame.entity.Board;
import com.ymd.businesshousegame.entity.Cell;
import com.ymd.businesshousegame.entity.CellType;
import com.ymd.businesshousegame.entity.Player;
import com.ymd.businesshousegame.repository.CellRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CellService {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private CellRepository cellDao;

    public Player handleCellLanding(Board board, Player player) {
        Cell cell = board.getBoardCells().get(player.getCurrentPositionOnBoard());
        CellType celltype = cell.getCellType();
        switch (celltype) {
            case JAIL -> player.setTotalBalance(player.getTotalBalance() - cell.getJailPenalty());
            case TREASURE -> player.setTotalBalance(player.getTotalBalance() + cell.getTreasureValue());
            case HOTEL -> handleHotel(player, cell);
            default -> logger.error("Unknown cell type: " + celltype);
        }
        return player;
    }

    private void handleHotel(Player player, Cell cell) {
        if (cell.getHotelOwner() == null) {
            player.setTotalBalance(player.getTotalBalance() - cell.getHotelWorth());
            cell.setHotelOwner(player);
            cellDao.save(cell);
        } else {
            player.setTotalBalance(player.getTotalBalance() - cell.getHotelRent());
        }
    }
}
