package jg.cryptodroid.model;

import jg.cryptodroid.enums.CoinList;
import jg.cryptodroid.service.MarketDataGenerator;

import java.util.List;
import java.util.stream.Collectors;

public class ExchangeModel {
    private MarketDataGenerator marketDataGenerator;
    private String marketName;
    private List<MarketFeesModel> marketFees;
    private String qureyUrl;

    public ExchangeModel(String url) {
        marketDataGenerator = new MarketDataGenerator(url);
        marketFees = marketDataGenerator.getList();
        marketName = marketDataGenerator.getName();
        qureyUrl = marketDataGenerator.getQureyUrl();
    }
    public List<CoinList> getCoinsList() {
        return marketFees
                .stream()
                .map(MarketFeesModel::getToken)
                .collect(Collectors.toList());
    }

    public String getExchangeName() {
        return marketName;
    }

    public double getExchangeFee(CoinList coinList) {
        return marketFees
                .stream()
                .filter(s -> s.getToken().equals(coinList))
                .map(MarketFeesModel::getExchangeFee)
                .findFirst()
                .orElseGet(() -> 0.0);
    }

    public double getWirhdrawalFee(CoinList coinList) {
        return marketFees
                .stream()
                .filter(s -> s.getToken().equals(coinList))
                .map(MarketFeesModel::getWithdrawalFee)
                .findFirst()
                .orElseGet(() -> 0.0);
    }

    public String getQueryUrl() {
        return qureyUrl;
    }
}
