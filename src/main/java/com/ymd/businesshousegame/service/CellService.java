package com.ymd.businesshousegame.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymd.businesshousegame.entity.Cell;
import com.ymd.businesshousegame.repo.CellDao;

@Service
public class CellService { 
	
	@Autowired
	private CellDao cellDao;

	public List<Cell> saveCells(List<Cell> cells){
		cells=cellDao.saveAll(cells);
		cellDao.flush();
		return cells;
	}
}
