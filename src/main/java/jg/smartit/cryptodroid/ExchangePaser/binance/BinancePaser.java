package jg.smartit.cryptodroid.ExchangePaser.binance;

import jg.smartit.cryptodroid.ExchangePaser.ExchangePaser;
import jg.smartit.cryptodroid.ExchangePaser.binance.mapper.BinanceMapper;
import jg.smartit.cryptodroid.ExchangePaser.binance.model.BinanceModel;
import jg.smartit.cryptodroid.annotation.MarketPaser;
import jg.smartit.cryptodroid.model.CoinList;
import jg.smartit.cryptodroid.model.ExchangeModel;
import org.springframework.web.client.RestClientException;

import java.util.ArrayList;
import java.util.List;


@MarketPaser(name = "BINANCE")
public class BinancePaser extends ExchangePaser {


    private BinanceMapper binanceMapper;
    private final String URL = "https://api.binance.com/api/v1/depth?symbol=";
    private List<CoinList> binanceCoins;


    public BinancePaser() {
        binanceMapper = new BinanceMapper();
        exchangeModel = new ExchangeModel("/static/binance.coin");
        binanceCoins = exchangeModel.getCoinsList();
    }

    @Override
    public boolean createData() {
        coinModels = new ArrayList<>();
        boolean error = true;
        for (CoinList binancecoin : binanceCoins) {
            try {
                coinModels
                        .add(binanceMapper
                                .map(restTemplate
                                        .getForObject(URL + binancecoin.name() + "BTC", BinanceModel.class), binancecoin));
            } catch (RestClientException e) {
                e.printStackTrace();
                System.out.println(binancecoin.name());
                error = false;
            }
        }
        return error;
    }
}
