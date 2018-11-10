package jg.cryptodroid.mapper;

import jg.cryptodroid.annotation.MarketType;
import jg.cryptodroid.enums.CoinList;
import jg.cryptodroid.model.BittrexModel;
import jg.cryptodroid.model.CoinModel;
import jg.cryptodroid.model.Order;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;
import java.util.stream.Collectors;

@MarketType(name = "BITTREX")
public class BittrexMapper implements ExchangeMapper {

    private BittrexModel bittrexModel;

    @Override
    public Optional<CoinModel> map(CoinList coinList, String queryUrl, String exchangeName) {
        RestTemplate restTemplate = new RestTemplate();
        bittrexModel =  restTemplate.getForObject(queryUrl.replace("{TAG}",coinList.name()).replace("{LTAG}",coinList.name().toLowerCase()), BittrexModel.class);
return Optional.of(new CoinModel(coinList,
        bittrexModel.getResult().getBuy()
                .stream()
        .map(s -> new Order(s.getRate(),s.getQuantity()))
        .collect(Collectors.toList()),
        bittrexModel.getResult().getSell()
        .stream()
        .map(s -> new Order(s.getRate(),s.getQuantity()))
        .collect(Collectors.toList())
        ,exchangeName));
    }
}
