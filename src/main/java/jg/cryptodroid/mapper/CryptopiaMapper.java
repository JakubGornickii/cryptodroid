package jg.cryptodroid.mapper;

import jg.cryptodroid.annotation.MarketType;
import jg.cryptodroid.model.CryptopiaModel;
import jg.cryptodroid.enums.CoinList;
import jg.cryptodroid.model.CoinModel;
import jg.cryptodroid.model.Order;
import org.springframework.web.client.RestTemplate;

import java.util.stream.Collectors;
@MarketType(name = "CRYPTOPIA")
public class CryptopiaMapper implements ExchangeMapper{

    private CryptopiaModel cryptopiaModel;

    @Override
    public CoinModel map(CoinList coinList,String queryUrl,String exchangeName) {
        RestTemplate restTemplate = new RestTemplate();
        cryptopiaModel =  restTemplate.getForObject(queryUrl.replace("{TAG}",coinList.name()).replace("{LTAG}",coinList.name().toLowerCase()), CryptopiaModel.class);

        return new CoinModel(coinList, cryptopiaModel.getData().getBuy()
                .stream()
                .map(s -> new Order(s.getPrice(), s.getVolume()))
                .collect(Collectors.toList()),
                cryptopiaModel.getData().getSell()
                        .stream()
                        .map(s -> new Order(s.getPrice(), s.getVolume()))
                        .collect(Collectors.toList()), exchangeName);
    }
}
