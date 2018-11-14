package jg.cryptodroid.mapper;

import jg.cryptodroid.annotation.MarketType;
import jg.cryptodroid.enums.CoinList;
import jg.cryptodroid.model.CoinModel;
import jg.cryptodroid.model.LbankModel;
import jg.cryptodroid.model.Order;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;
import java.util.stream.Collectors;
@MarketType(name = "LBANK")
public class LbankMapper implements ExchangeMapper {

    private LbankModel lbankModel;

    @Override
    public Optional<CoinModel> map(CoinList coinList, String queryUrl, String exchangeName) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            lbankModel = restTemplate.getForObject(queryUrl.replace("{TAG}", coinList.name()).replace("{LTAG}", coinList.name().toLowerCase()), LbankModel
                    .class);
            return Optional.of(new CoinModel(coinList,
                    lbankModel.getBids()
                            .stream()
                            .map(s -> new Order(s.get(0),s.get(1)))
                            .collect(Collectors.toList()),
                    lbankModel.getAsks()
                            .stream()
                            .map(s -> new Order(s.get(0),s.get(1)))
                            .collect(Collectors.toList())
                    , exchangeName));
        } catch (RuntimeException e) {
            System.err.println(coinList);
            return Optional.empty();
        }
    }
}
