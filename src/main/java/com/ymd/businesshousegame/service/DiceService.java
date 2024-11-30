package com.ymd.businesshousegame.service;

import com.ymd.businesshousegame.entity.Dice;
import com.ymd.businesshousegame.repository.DiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DiceService {

    @Autowired
    private DiceRepository diceRepo;

    public Dice setup(String diceOutputsStr) {
        return diceRepo.save(new Dice(getDiceOutputs(diceOutputsStr)));
    }

    private static List<Integer> getDiceOutputs(String diceOutputsStr) {
        return Arrays.stream(diceOutputsStr.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    public int giveDice(Dice dice) {
        int currentOutputIndex = dice.getLastUsedOutputIndex() + 1;
        int output = dice.getOutputs().get(currentOutputIndex);
        dice.setLastUsedOutputIndex(currentOutputIndex);
        diceRepo.save(dice);
        return output;
    }

}
