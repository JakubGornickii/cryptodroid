package jg.cryptodroid.mapper;

import jg.cryptodroid.annotation.MarketType;
import jg.cryptodroid.enums.CoinList;
import jg.cryptodroid.model.BiboxModel;
import jg.cryptodroid.model.CoinModel;
import jg.cryptodroid.model.Order;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;
import java.util.stream.Collectors;

@MarketType(name = "BIBOX")
public class BiboxMapper implements ExchangeMapper{
    private BiboxModel biboxModel;
    @Override
    public Optional<CoinModel> map(CoinList coinList, String queryUrl, String exchangeName) {
        RestTemplate restTemplate = new RestTemplate();
        try{
        biboxModel =  restTemplate.getForObject(queryUrl.replace("{TAG}",coinList.name()).replace("{LTAG}",coinList.name().toLowerCase()), BiboxModel.class);
        return Optional.of(new CoinModel(coinList,
                biboxModel.getResult().getBids()
                        .stream()
                        .map(s -> new Order(Double.valueOf(s.getPrice()),Double.valueOf(s.getVolume())))
                        .collect(Collectors.toList()),
                biboxModel.getResult().getAsks()
                        .stream()
                        .map(s -> new Order(Double.valueOf(s.getPrice()),Double.valueOf(s.getVolume())))
                        .collect(Collectors.toList())
                ,exchangeName));
    }catch (RuntimeException e){
            System.err.println(coinList);
        return Optional.empty();}
    }
}
