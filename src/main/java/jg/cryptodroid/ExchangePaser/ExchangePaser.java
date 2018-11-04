package jg.cryptodroid.ExchangePaser;

import jg.cryptodroid.mapper.ExchangeMapper;
import jg.cryptodroid.service.CreateAnnotationClass;
import jg.cryptodroid.service.TxtFileReader;
import jg.cryptodroid.exchangebase.ExchangeBase;
import jg.cryptodroid.enums.CoinList;
import jg.cryptodroid.model.CoinModel;
import jg.cryptodroid.model.ExchangeModel;
import org.springframework.web.client.RestClientException;

import java.util.ArrayList;
import java.util.List;

public class ExchangePaser {
    private CreateAnnotationClass createAnnotationClass;
    private ExchangeMapper exchangeMapper;
    private ExchangeModel exchangeModel;
    private TxtFileReader txtFileReader;
    private List<CoinModel> coinModels;
    private  List<CoinList> exchangeCoins;
    private String queryUrl;

    public ExchangePaser(String url) {
        createAnnotationClass = new CreateAnnotationClass();
        exchangeModel = new ExchangeModel(url);
        queryUrl = exchangeModel.getQueryUrl();
        this.txtFileReader = new TxtFileReader();
        coinModels = new ArrayList<>();
        exchangeCoins = exchangeModel.getCoinsList();
        exchangeMapper = createAnnotationClass.findAnnotatedClasses(exchangeModel.getExchangeName());
        ExchangeBase.EXCHANGES.add(this);
    }

    public  boolean createData(){
        coinModels = new ArrayList<>();
        boolean error = true;
        for (CoinList exchangeCoin : exchangeCoins) {

            try {
                coinModels
                        .add(exchangeMapper
                                .map(exchangeCoin,queryUrl,exchangeModel.getExchangeName()));
            } catch (RestClientException e) {
                e.printStackTrace();
                error = false;
            }

        }
        return error;
    };
    public List<CoinModel> getData() {
        return coinModels;
    }
    public ExchangeModel getExchangeModel(){
        return exchangeModel;
    }
}
