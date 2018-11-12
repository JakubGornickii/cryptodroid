package jg.cryptodroid.mapper;

import jg.cryptodroid.annotation.MarketType;
import jg.cryptodroid.enums.CoinList;
import jg.cryptodroid.model.CoinModel;
import jg.cryptodroid.model.OkexModel;
import jg.cryptodroid.model.Order;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@MarketType(name = "OKEX")
public class OkexMapper implements ExchangeMapper {

    OkexModel okexModel;

    public Optional<CoinModel> map(CoinList coinList, String queryUrl, String exchangeName) {
        RestTemplate restTemplate = new RestTemplate();
        okexModel = restTemplate.getForObject(queryUrl.replace("{TAG}", coinList.name()).replace("{LTAG}", coinList.name().toLowerCase()), OkexModel.class);


        List<List<String>> askList = okexModel.getAsks();
        List<List<String>> bidList = okexModel.getBids();
        List<Order> askOrderList = askList.stream()
                .map(s -> new Order(Double.valueOf(s.get(0)), Double.valueOf(s.get(1))))
                .collect(Collectors.toList());
        List<Order> bidOrderList = bidList.stream()
                .map(s -> new Order(Double.valueOf(s.get(0)), Double.valueOf(s.get(1))))
                .collect(Collectors.toList());
        return Optional.of(new CoinModel(coinList, bidOrderList, askOrderList, exchangeName));
    }
}
