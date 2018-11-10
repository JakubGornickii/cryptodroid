package jg.cryptodroid.service;

import jg.cryptodroid.coinsbase.CoinsBase;
import org.springframework.stereotype.Service;

@Service
public class ArbitrageThread implements Runnable {

    private FindArbitrage findArbitrage;

    public ArbitrageThread(FindArbitrage findArbitrage) {
        this.findArbitrage = findArbitrage;
    }

    @Override
    public void run() {
        Thread.currentThread().setPriority(1);
        while (true) {
            if (CoinsBase.MARKETS_COINS_DATA.size() > 10) {
                findArbitrage.generateList();
                findArbitrage.getArbitrage().stream().filter(s -> s.getEarnPercentage() >= 0.1).forEach(System.out::println);
                System.out.println("--------------------------------------------------------------------------------------");
                System.out.println("ARBITAGE "+Thread.currentThread().getName()+" "+Thread.currentThread().getPriority());
            }
        }
    }
}
