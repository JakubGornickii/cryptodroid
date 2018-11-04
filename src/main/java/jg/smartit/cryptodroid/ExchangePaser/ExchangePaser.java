package jg.smartit.cryptodroid.ExchangePaser;

import jg.smartit.cryptodroid.exchangebase.ExchangeBase;
import jg.smartit.cryptodroid.model.CoinModel;
import jg.smartit.cryptodroid.model.ExchangeModel;
import jg.smartit.cryptodroid.service.TxtFileReader;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public abstract class ExchangePaser {
    protected ExchangeModel exchangeModel;
    protected RestTemplate restTemplate;
    protected TxtFileReader txtFileReader;
    protected List<CoinModel> coinModels;

    public ExchangePaser() {
        restTemplate = new RestTemplate();
        this.txtFileReader = new TxtFileReader();
        coinModels = new ArrayList<>();
        ExchangeBase.EXCHANGES.add(this);
    }

    public abstract boolean createData();
    public List<CoinModel> getData() {
        return coinModels;
    }
    public ExchangeModel getExchangeModel(){
        return exchangeModel;
    }
}
