package jg.cryptodroid.mapper;

import jg.cryptodroid.annotation.MarketType;
import jg.cryptodroid.enums.CoinList;
import jg.cryptodroid.model.CoinModel;
import jg.cryptodroid.model.HitbtcModel;
import jg.cryptodroid.model.Order;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;
import java.util.stream.Collectors;

@MarketType(name = "HITBTC")
public class HitbtcMapper implements ExchangeMapper {

HitbtcModel hitbtcModel;


    @Override
    public Optional<CoinModel> map(CoinList coinList, String queryUrl, String exchangeName) {
        RestTemplate restTemplate = new RestTemplate();
        hitbtcModel = restTemplate.getForObject(queryUrl.replace("{TAG}",coinList.name()).replace("{LTAG}",coinList.name().toLowerCase()), HitbtcModel.class);

        return Optional.of(new CoinModel(coinList,
                hitbtcModel.getBid().stream()
                        .map(s -> new Order(Double.parseDouble(s.getPrice()),Double.parseDouble(s.getSize())))
                        .collect(Collectors.toList()),
                hitbtcModel.getAsk().stream()
                        .map(s -> new Order(Double.parseDouble(s.getPrice()),Double.parseDouble(s.getSize())))
                        .collect(Collectors.toList()), exchangeName));


    }
}
