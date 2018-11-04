package jg.smartit.cryptodroid.service;

import jg.smartit.cryptodroid.model.CoinModel;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CallRestService {

    public CoinModel callRest(CoinModel coinModel,String url) {
        RestTemplate restTemplate = new RestTemplate();

            coinModel = restTemplate.getForObject(url,CoinModel.class);
            return coinModel;
    }

}