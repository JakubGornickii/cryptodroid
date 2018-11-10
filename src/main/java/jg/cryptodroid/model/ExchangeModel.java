package jg.cryptodroid.model;

import jg.cryptodroid.enums.CoinList;
import jg.cryptodroid.service.ExchangeDataGenerator;

import java.util.List;
import java.util.stream.Collectors;

public class ExchangeModel {
    private ExchangeDataGenerator exchangeDataGenerator;
    private String marketName;
    private List<MarketFeesModel> marketFees;
    private String qureyUrl;

    public ExchangeModel(String url) {
        exchangeDataGenerator = new ExchangeDataGenerator(url);
        marketFees = exchangeDataGenerator.getList();
        marketName = exchangeDataGenerator.getName();
        qureyUrl = exchangeDataGenerator.getQureyUrl();
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

    public List<MarketFeesModel> getMarketFees() {
        return marketFees;
    }
}
