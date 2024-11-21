package com.ymd.businesshousegame.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OrderBy;

@Entity
public class Dice {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;

	@ElementCollection
	@CollectionTable(name = "dice_outputs", joinColumns = @JoinColumn(name = "dice_id"))
	@Column(name = "output")
	private List<Integer> outputs = new ArrayList<>();

	private int lastUsedOutputIndex = -1;

	public int getLastUsedOutputIndex() {
		return lastUsedOutputIndex;
	}

	public void setLastUsedOutputIndex(int lastUsedOutputIndex) {
		this.lastUsedOutputIndex = lastUsedOutputIndex;
	}

	public List<Integer> getOutputs() {
		return outputs;
	}

	public void setOutputs(List<Integer> outputs) {
		this.outputs = outputs;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

}
