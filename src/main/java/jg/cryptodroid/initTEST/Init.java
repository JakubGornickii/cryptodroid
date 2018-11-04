package jg.cryptodroid.initTEST;

import jg.cryptodroid.ExchangePaser.ExchangePaser;
import jg.cryptodroid.coinsbase.CoinsBase;
import jg.cryptodroid.service.FindArbitrage;
import jg.cryptodroid.service.MarketThread;
import jg.cryptodroid.service.TxtFileReader;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class Init {

    private FindArbitrage findArbitrage;

    public Init(FindArbitrage findArbitrage) {
        this.findArbitrage = findArbitrage;
    }

    @PostConstruct
    public synchronized void init() {
        TxtFileReader txtFileReader = new TxtFileReader();
        String[] marketList = txtFileReader.readExchangeList();
        for (String s : marketList) {
            ExchangePaser exchangePaser = new ExchangePaser(s);
            MarketThread marketThread = new MarketThread(exchangePaser);
            Thread thread = new Thread(marketThread);
            thread.start();
        }


        while (true) {
            if (CoinsBase.MARKETS_COINS_DATA.size() > 10) {
                findArbitrage.generateList();
                findArbitrage.getArbitrage().stream().filter(s -> s.getEarnPercentage() > 0.1).forEach(System.out::println);
                System.out.println("--------------------------------------------------------------------------------------");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
