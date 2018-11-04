package jg.smartit.cryptodroid.ExchangePaser.bitbay;

import jg.smartit.cryptodroid.ExchangePaser.ExchangePaser;
import jg.smartit.cryptodroid.ExchangePaser.bitbay.mapper.BitbayMapper;
import jg.smartit.cryptodroid.ExchangePaser.bitbay.model.BitbayModel;
import jg.smartit.cryptodroid.annotation.MarketPaser;
import jg.smartit.cryptodroid.model.CoinList;
import jg.smartit.cryptodroid.model.ExchangeModel;
import org.springframework.web.client.RestClientException;

import java.util.ArrayList;
import java.util.List;

@MarketPaser(name = "BITBAY")
public class BitbayPaser extends ExchangePaser {


    private BitbayMapper bitbayMapper;
    private final String URL = "https://bitbay.net/API/Public/";
    private final String URL1 = "/orderbook.json";
    private List<CoinList> bitbayCoins;

    public BitbayPaser() {
        exchangeModel = new ExchangeModel("/static/bitbay.coin");
        this.bitbayMapper = new BitbayMapper();
        bitbayCoins = exchangeModel.getCoinsList();
    }

    @Override
    public boolean createData() {
        coinModels = new ArrayList<>();
        boolean error = true;
        for (CoinList bitbayCoin : bitbayCoins) {

            try {
                coinModels
                        .add(bitbayMapper
                                .map(restTemplate
                                        .getForObject(URL + bitbayCoin.name() + "BTC" + URL1, BitbayModel.class), bitbayCoin));
            } catch (RestClientException e) {
                e.printStackTrace();
                error = false;
            }

        }
        return error;
    }

}
