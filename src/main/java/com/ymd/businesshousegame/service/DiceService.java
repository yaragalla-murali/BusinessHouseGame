package com.ymd.businesshousegame.service;

import com.ymd.businesshousegame.entity.Dice;
import com.ymd.businesshousegame.repository.DiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DiceService {

    @Autowired
    private DiceRepository diceRepo;

    public Dice setup(String diceOutputsStr) {
        String[] diceOutputStrArray = diceOutputsStr.split(",");
        List<Integer> diceOutputs = new ArrayList<>();
        Dice dice = new Dice();
        for (String diceOutputStr : diceOutputStrArray) {
            Integer output = Integer.parseInt(diceOutputStr);
            diceOutputs.add(output);
        }
        dice.setOutputs(diceOutputs);
        dice = diceRepo.save(dice);
        diceRepo.flush();
        return dice;

    }

    public Dice saveDice(Dice dice) {
        dice = diceRepo.save(dice);
        diceRepo.flush();
        return dice;
    }

    public int giveDice(Dice dice) {
        int currentOutputIndex = dice.getLastUsedOutputIndex() + 1;
        int output = dice.getOutputs().get(currentOutputIndex);
        dice.setLastUsedOutputIndex(currentOutputIndex);
        diceRepo.save(dice);
        return output;
    }

}
