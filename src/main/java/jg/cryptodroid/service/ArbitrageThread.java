package jg.cryptodroid.service;

import jg.cryptodroid.inmemorydatabase.ArbitrageBase;
import jg.cryptodroid.model.Arbitrage;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArbitrageThread implements Runnable {

    private FindArbitrage findArbitrage;

    public ArbitrageThread(FindArbitrage findArbitrage) {
        this.findArbitrage = findArbitrage;
    }

    @Override
    public void run() {
        Thread.currentThread().setPriority(1);
        List<Arbitrage> oldList = new ArrayList<>();
        List<Arbitrage> newList;
        while (true) {
                findArbitrage.generateList();
                if (oldList.size() == 0) {
                    oldList = findArbitrage.getArbitrage().stream().filter(s -> s.getEarnPercentage() >= 0.5).collect(Collectors.toList());
                    ArbitrageBase.ARBITRAGES.addAll(oldList);
                }
                newList = findArbitrage.getArbitrage().stream().filter(s -> s.getEarnPercentage() >= 0.5).collect(Collectors.toList());
                ArbitrageBase.ARBITRAGES.removeAll(oldList);
                ArbitrageBase.ARBITRAGES.addAll(newList);
                oldList = new ArrayList<>(newList);
                newList.clear();
            }
        }
    }

