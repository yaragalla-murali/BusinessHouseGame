package com.ymd.businesshousegame.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Dice {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer Id;

	private int lastUsedOutputIndex;

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public int getLastUsedOutputIndex() {
		return lastUsedOutputIndex;
	}

	public void setLastUsedOutputIndex(int lastUsedOutputIndex) {
		this.lastUsedOutputIndex = lastUsedOutputIndex;
	}

}
