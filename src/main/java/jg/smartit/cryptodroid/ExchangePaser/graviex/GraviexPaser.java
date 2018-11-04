package jg.smartit.cryptodroid.ExchangePaser.graviex;

import jg.smartit.cryptodroid.ExchangePaser.ExchangePaser;
import jg.smartit.cryptodroid.ExchangePaser.graviex.mapper.GraviexMapper;
import jg.smartit.cryptodroid.ExchangePaser.graviex.model.GraviexModel;
import jg.smartit.cryptodroid.annotation.MarketPaser;
import jg.smartit.cryptodroid.model.CoinList;
import jg.smartit.cryptodroid.model.ExchangeModel;
import org.springframework.web.client.RestClientException;

import java.util.ArrayList;
import java.util.List;

@MarketPaser(name = "GRAVIEX")
public class GraviexPaser extends ExchangePaser {

    private GraviexMapper graviexMapper;
    private final String URL = "https://graviex.net:443//api/v2/order_book.json?market=";
    private List<CoinList> graviexCoins;

    public GraviexPaser() {
        exchangeModel = new ExchangeModel("/static/graviex.coin");
        graviexMapper = new GraviexMapper();
        graviexCoins = exchangeModel.getCoinsList();
    }

    @Override
    public boolean createData() {
        coinModels = new ArrayList<>();
        boolean error = true;
        for (CoinList graviexCoins : graviexCoins) {

            try {
                coinModels
                        .add(graviexMapper
                                .map(restTemplate
                                        .getForObject(URL + graviexCoins.name().toLowerCase() + "btc", GraviexModel.class), graviexCoins));
            } catch (RestClientException e) {
                e.printStackTrace();
                error = false;
            }

        }
        return error;
    }


}
