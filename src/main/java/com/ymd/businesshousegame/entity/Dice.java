package com.ymd.businesshousegame.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Dice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    @ElementCollection
    @CollectionTable(name = "dice_outputs", joinColumns = @JoinColumn(name = "dice_id"))
    //@Column(name = "output")
    private List<Integer> outputs = new ArrayList<>();

    private int lastUsedOutputIndex = -1;

    public Dice() {
    }

    public Dice(List<Integer> diceOutputs) {
        this.outputs = diceOutputs;
    }

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
