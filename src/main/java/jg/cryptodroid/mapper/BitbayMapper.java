package jg.cryptodroid.mapper;
import jg.cryptodroid.annotation.MarketType;
import jg.cryptodroid.enums.CoinList;
import jg.cryptodroid.model.BitbayModel;
import jg.cryptodroid.model.CoinModel;
import jg.cryptodroid.model.Order;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@MarketType(name = "BITBAY")
public class BitbayMapper implements ExchangeMapper{

    private BitbayModel bitbayModel;
@Override
    public CoinModel map(CoinList coinList, String queryUrl, String exchangeName) {
        RestTemplate restTemplate = new RestTemplate();
        bitbayModel =  restTemplate.getForObject(queryUrl.replace("{TAG}",coinList.name()).replace("{LTAG}",coinList.name().toLowerCase()), BitbayModel.class);

    List<List<Double>> askList = bitbayModel.getAsks();
    List<List<Double>> bidList = bitbayModel.getBids();
    List<Order> askOrderList = askList.stream()
            .map(s -> new Order(s.get(0),s.get(1)))
            .collect(Collectors.toList());
    List<Order> bidOrderList = bidList.stream()
            .map(s -> new Order(s.get(0),s.get(1)))
            .collect(Collectors.toList());
    return new CoinModel(coinList,bidOrderList,askOrderList,exchangeName);
}
}
