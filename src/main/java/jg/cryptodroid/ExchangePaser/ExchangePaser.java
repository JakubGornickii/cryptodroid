package jg.cryptodroid.ExchangePaser;

import jg.cryptodroid.enums.CoinList;
import jg.cryptodroid.inmemorydatabase.ExchangeBase;
import jg.cryptodroid.mapper.ExchangeMapper;
import jg.cryptodroid.model.CoinModel;
import jg.cryptodroid.model.ExchangeModel;
import jg.cryptodroid.service.CreateAnnotationClass;
import jg.cryptodroid.service.ServerDataService;
import org.springframework.web.client.RestClientException;

import java.util.ArrayList;
import java.util.List;

public class ExchangePaser {
    private static int SECOND_TO_GENERATE_LIST_OF_COINS = 30;
    private CreateAnnotationClass createAnnotationClass;
    private ExchangeMapper exchangeMapper;
    private ExchangeModel exchangeModel;
    private List<CoinModel> coinModels;
    private List<CoinList> exchangeCoins;
    private String queryUrl;
    private int numberOfCoins;
    private boolean active;
    private long lastUpdate;


    public ExchangePaser(String url) {
        active = false;
        createAnnotationClass = new CreateAnnotationClass();
        exchangeModel = new ExchangeModel(url);
        queryUrl = exchangeModel.getQueryUrl();
        coinModels = new ArrayList<>();

        exchangeMapper = createAnnotationClass.findAnnotatedClasses(exchangeModel.getExchangeName());
        exchangeCoins = exchangeModel.getCoinsList();
        numberOfCoins = exchangeCoins.size();
        ExchangeBase.EXCHANGES.add(this);
    }

    public void createData() {
        coinModels = new ArrayList<>();
        int coinCreated = 0;
        double fps = numberOfCoins;
        double timePerTick = 1000000000 / fps;
        double delta = 0;
        long now;

        long lastTime = System.nanoTime();

        for (int i = 0; i < exchangeCoins.size(); i++) {

            while (coinCreated == i) {
                now = System.nanoTime();
                delta += (now - lastTime) / timePerTick;
                lastTime = now;
                if (delta >= SECOND_TO_GENERATE_LIST_OF_COINS) {
                    try {
                        exchangeMapper.map(exchangeCoins.get(i), queryUrl, exchangeModel.getExchangeName()).ifPresent(s ->
                        {
                            coinModels.add(s);
                            ServerDataService.queres();
                            active = true;
                        });

                    } catch (RestClientException e) {
                        active = false;
                        e.printStackTrace();
                    }
                    coinCreated++;

                    delta -= SECOND_TO_GENERATE_LIST_OF_COINS;
                }
            }
        }
    }

    public List<CoinModel> getData() {

        lastUpdate = System.currentTimeMillis();
        return coinModels;
    }

    public ExchangeModel getExchangeModel() {
        return exchangeModel;
    }

    public boolean isActive() {
        return active;
    }

    public long getLastUpdate() {
        return lastUpdate;
    }
}
