package jg.cryptodroid.mapper;

import jg.cryptodroid.annotation.MarketType;
import jg.cryptodroid.enums.CoinList;
import jg.cryptodroid.model.CoinModel;
import jg.cryptodroid.model.Crex24Model;
import jg.cryptodroid.model.Order;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;
import java.util.stream.Collectors;

@MarketType(name = "CREX24")
public class Crex24Mapper implements ExchangeMapper {

    private Crex24Model crex24Model;

    @Override
    public Optional<CoinModel> map(CoinList coinList, String queryUrl, String exchangeName) {
        RestTemplate restTemplate = new RestTemplate();
        boolean error = true;
        boolean firstLoop = true;
        while (error) {
            if (firstLoop) {
                firstLoop = false;
                error = false;
            }
            try {
                crex24Model = restTemplate.getForObject(queryUrl.replace("{TAG}", coinList.name()).replace("{LTAG}", coinList.name().toLowerCase()), Crex24Model.class);
            } catch (HttpClientErrorException e) {
                if (error) {
                    System.err.println("Błąd pobierania danych z adresu " + queryUrl.replace("{TAG}", coinList.name()).replace("{LTAG}", coinList.name().toLowerCase()));
                    return Optional.empty();
                }
                error = true;
            }
        }
        return Optional.of(new CoinModel(coinList,
                crex24Model.getBuyLevels()
                        .stream()
                        .map(s -> new Order(s.getPrice(), s.getVolume()))
                        .collect(Collectors.toList()),
                crex24Model.getSellLevels()
                        .stream()
                        .map(s -> new Order(s.getPrice(), s.getVolume()))
                        .collect(Collectors.toList()), exchangeName));
    }
}
