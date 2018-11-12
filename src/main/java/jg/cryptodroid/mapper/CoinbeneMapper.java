package jg.cryptodroid.mapper;

import jg.cryptodroid.annotation.MarketType;
import jg.cryptodroid.enums.CoinList;
import jg.cryptodroid.model.CoinModel;
import jg.cryptodroid.model.CoinbeneModel;
import jg.cryptodroid.model.Order;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;
import java.util.stream.Collectors;

@MarketType(name = "COINBENE")
public class CoinbeneMapper implements ExchangeMapper {
    private CoinbeneModel coinbeneModel;


    @Override
    public Optional<CoinModel> map(CoinList coinList, String queryUrl, String exchangeName) {
        RestTemplate restTemplate = new RestTemplate();
        coinbeneModel =  restTemplate.getForObject(queryUrl.replace("{TAG}",coinList.name()).replace("{LTAG}",coinList.name().toLowerCase()), CoinbeneModel.class);
        System.out.println(coinList);
        return Optional.of(new CoinModel(coinList, coinbeneModel.getOrderbook().getBids()
                .stream()
                .map(s -> new Order(s.getPrice(), s.getQuantity()))
                .collect(Collectors.toList()),
                coinbeneModel.getOrderbook().getAsks()
                        .stream()
                        .map(s -> new Order(s.getPrice(), s.getQuantity()))
                        .collect(Collectors.toList()), exchangeName));
    }
}
