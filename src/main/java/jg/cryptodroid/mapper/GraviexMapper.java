package jg.cryptodroid.mapper;

import jg.cryptodroid.annotation.MarketType;
import jg.cryptodroid.model.GraviexModel;
import jg.cryptodroid.enums.CoinList;
import jg.cryptodroid.model.CoinModel;
import jg.cryptodroid.model.Order;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;
import java.util.stream.Collectors;

@MarketType(name = "GRAVIEX")
public class GraviexMapper implements ExchangeMapper {


    GraviexModel graviexModel;
    @Override
    public Optional<CoinModel> map(CoinList coinList, String queryUrl, String exchangeName) {
        RestTemplate restTemplate = new RestTemplate();
        graviexModel = restTemplate.getForObject(queryUrl.replace("{TAG}",coinList.name()).replace("{LTAG}",coinList.name().toLowerCase()), GraviexModel.class);


        return Optional.of(new CoinModel(coinList,
                graviexModel.getBids().stream()
                        .map(s -> new Order(Double.parseDouble(s.getPrice()),Double.parseDouble(s.getVolume())))
                        .collect(Collectors.toList()),
                graviexModel.getAsks().stream()
                        .map(s -> new Order(Double.parseDouble(s.getPrice()),Double.parseDouble(s.getVolume())))
                        .collect(Collectors.toList()), exchangeName));


    }
}
