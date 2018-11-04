package jg.cryptodroid.mapper;

import jg.cryptodroid.annotation.MarketType;
import jg.cryptodroid.enums.CoinList;
import jg.cryptodroid.model.BinanceModel;
import jg.cryptodroid.model.CoinModel;
import jg.cryptodroid.model.Order;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@MarketType(name = "BINANCE")
public class BinanceMapper implements ExchangeMapper{

    BinanceModel binanceModel;

    public CoinModel map(CoinList coinList, String queryUrl, String exchangeName) {
        RestTemplate restTemplate = new RestTemplate();
        binanceModel =  restTemplate.getForObject(queryUrl.replace("{TAG}",coinList.name()).replace("{LTAG}",coinList.name().toLowerCase()), BinanceModel.class);

        List<List<Object>> askList = binanceModel.getAsks();
        List<List<Object>> bidList = binanceModel.getBids();
        List<Order> askOrderList = askList.stream()
                .map(s -> new Order(Double.parseDouble((String) s.get(0)),Double.parseDouble((String) s.get(1))))
                .collect(Collectors.toList());
        List<Order> bidOrderList = bidList.stream()
                .map(s -> new Order(Double.parseDouble((String) s.get(0)),Double.parseDouble((String) s.get(1))))
                .collect(Collectors.toList());
        return new CoinModel(coinList,bidOrderList,askOrderList,exchangeName);
    }
}
