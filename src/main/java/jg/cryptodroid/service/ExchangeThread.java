package jg.cryptodroid.service;

import jg.cryptodroid.ExchangePaser.ExchangePaser;
import jg.cryptodroid.inmemorydatabase.CoinsBase;
import jg.cryptodroid.model.CoinModel;

import java.util.ArrayList;
import java.util.List;

public class ExchangeThread implements Runnable {

    private String marketName;
    private ExchangePaser exchangePaser;
    private List<CoinModel> oldList = new ArrayList<>();
    private List<CoinModel> newList = new ArrayList<>();

    public ExchangeThread(ExchangePaser exchangePaser) {
        this.exchangePaser = exchangePaser;
        this.marketName = exchangePaser.getExchangeModel().getExchangeName();
    }

    @Override
    public synchronized void run() {
        Thread.currentThread().setPriority(1);
        exchangePaser.createData();
        oldList = exchangePaser.getData();
        CoinsBase.MARKETS_COINS_DATA.addAll(oldList);
    System.out.println("("+Thread.currentThread().getName()+") "+marketName+": Pomyślnie wygenerowano liste kupna/sprzedaży dla "+oldList.size()+" kryptowalut");
        while (true) {

            exchangePaser.createData();
            newList = exchangePaser.getData();
            CoinsBase.MARKETS_COINS_DATA.removeAll(oldList);
            CoinsBase.MARKETS_COINS_DATA.addAll(newList);
            oldList = new ArrayList<>(newList);

        }
    }
}
