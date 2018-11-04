package jg.cryptodroid.service;

import jg.cryptodroid.model.MarketFeesModel;
import jg.cryptodroid.enums.CoinList;

import java.util.ArrayList;
import java.util.List;

public class MarketDataGenerator {
    private String name;
    private int numberOfCoins;
    private List<MarketFeesModel> marketFees;
    private String qureyUrl;

    public MarketDataGenerator(String url) {
        marketFees = new ArrayList<>();
        generateList(url);
    }

    private void generateList(String url) {
        TxtFileReader txtFileReader = new TxtFileReader();
        String[] data = txtFileReader.readExchangeData(url);
        numberOfCoins = Integer.parseInt(data[0]);
        name = data[1];
        qureyUrl = data[2];
        for (int i = 0; i < numberOfCoins; i++) {
            String[] tokenData = data[i+3].split(",");
            marketFees.add(new MarketFeesModel(CoinList.valueOf(tokenData[0])
                    ,Double.parseDouble(tokenData[1])
                    ,Double.parseDouble(tokenData[2])));
        }
    }

    public List<MarketFeesModel> getList() {
        return marketFees;
    }

    public String getName() {
        return name;
    }

    public String getQureyUrl() {
        return qureyUrl;
    }
}
