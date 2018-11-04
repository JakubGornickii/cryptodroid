package jg.cryptodroid.service;

import jg.cryptodroid.ExchangePaser.ExchangePaser;
import jg.cryptodroid.coinsbase.CoinsBase;
import jg.cryptodroid.model.CoinModel;

import java.util.ArrayList;
import java.util.List;

public class MarketThread implements Runnable {

    private String marketName;
    private ExchangePaser exchangePaser;
    private List<CoinModel> oldList = new ArrayList<>();
    private List<CoinModel> newList = new ArrayList<>();

    public MarketThread(ExchangePaser exchangePaser) {
        this.exchangePaser = exchangePaser;
        this.marketName = exchangePaser.getExchangeModel().getExchangeName();
    }

    @Override
    public synchronized void run() {
        boolean error = exchangePaser.createData();
        oldList = exchangePaser.getData();
        CoinsBase.MARKETS_COINS_DATA.addAll(oldList);
if (error){
    System.out.println("("+Thread.currentThread().getName()+") "+marketName+": Pomyślnie wygenerowano liste kupna/sprzedaży dla "+oldList.size()+" kryptowalut");
}
        while (error) {
            error = exchangePaser.createData();
            newList = exchangePaser.getData();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                error = false;
            }
            CoinsBase.MARKETS_COINS_DATA.removeAll(oldList);
            CoinsBase.MARKETS_COINS_DATA.addAll(newList);
            oldList = new ArrayList<>(newList);
        }
    }
}