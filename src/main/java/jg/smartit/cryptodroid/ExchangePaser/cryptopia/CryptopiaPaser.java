package jg.smartit.cryptodroid.ExchangePaser.cryptopia;

import jg.smartit.cryptodroid.ExchangePaser.ExchangePaser;
import jg.smartit.cryptodroid.ExchangePaser.cryptopia.mapper.CryptopiaMapper;
import jg.smartit.cryptodroid.ExchangePaser.cryptopia.model.CryptopiaModel;
import jg.smartit.cryptodroid.annotation.MarketPaser;
import jg.smartit.cryptodroid.model.CoinList;
import jg.smartit.cryptodroid.model.ExchangeModel;
import org.springframework.web.client.RestClientException;

import java.util.ArrayList;
import java.util.List;


@MarketPaser(name = "CRYPTOPIA")
public class CryptopiaPaser extends ExchangePaser {


    private CryptopiaMapper cryptopiaMapper;
    private final String URL = "https://www.cryptopia.co.nz/api/GetMarketOrders/";
    private final String URL1 = "_BTC/50";
    private List<CoinList> cryptopiaCoins;

    public CryptopiaPaser() {
        exchangeModel = new ExchangeModel("/static/cryptopia.coin");
        this.cryptopiaMapper = new CryptopiaMapper();
        cryptopiaCoins = exchangeModel.getCoinsList();
    }

    @Override
    public boolean createData() {
        coinModels = new ArrayList<>();
        boolean error = true;
        for (CoinList cryptopiaCoin : cryptopiaCoins) {

            try {
                coinModels
                        .add(cryptopiaMapper
                                .map(restTemplate
                                        .getForObject(URL + cryptopiaCoin.name() + URL1, CryptopiaModel.class), cryptopiaCoin));
            } catch (RestClientException e) {
                e.printStackTrace();
                error = false;
            }

        }
        return error;
    }
}
