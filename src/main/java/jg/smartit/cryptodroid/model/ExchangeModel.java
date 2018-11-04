package jg.smartit.cryptodroid.model;

import jg.smartit.cryptodroid.model.fees.MarketFeesListGenerator;
import jg.smartit.cryptodroid.model.fees.MarketFeesModel;

import java.util.List;
import java.util.stream.Collectors;

public class ExchangeModel {
    private MarketFeesListGenerator marketFeesListGenerator;
    private String marketName;
    private List<MarketFeesModel> marketFees;

    public ExchangeModel(String url) {
        marketFeesListGenerator = new MarketFeesListGenerator(url);
        marketFees = this.marketFeesListGenerator.getList();
        marketName = this.marketFeesListGenerator.getName();
    }
    public List<CoinList> getCoinsList() {
        return marketFees
                .stream()
                .map(MarketFeesModel::getToken)
                .collect(Collectors.toList());
    }

    public String getMarketName() {
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
}
