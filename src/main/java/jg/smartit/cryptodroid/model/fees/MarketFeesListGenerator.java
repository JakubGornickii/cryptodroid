package jg.smartit.cryptodroid.model.fees;

import jg.smartit.cryptodroid.model.CoinList;
import jg.smartit.cryptodroid.service.TxtFileReader;

import java.util.ArrayList;
import java.util.List;

public class MarketFeesListGenerator {
    private String name;
    private int numberOfCoins;
    private List<MarketFeesModel> marketFees;

    public MarketFeesListGenerator(String url) {
        marketFees = new ArrayList<>();
        generateList(url);
    }

    private void generateList(String url) {
        TxtFileReader txtFileReader = new TxtFileReader();
        String[] data = txtFileReader.read(url);
        numberOfCoins = Integer.parseInt(data[0]);
        name = data[1];
        for (int i = 0; i < numberOfCoins; i++) {
            String[] tokenData = data[i+2].split(",");
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
}
