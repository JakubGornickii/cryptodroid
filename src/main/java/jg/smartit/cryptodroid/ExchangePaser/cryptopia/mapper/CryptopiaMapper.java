package jg.smartit.cryptodroid.ExchangePaser.cryptopia.mapper;

import jg.smartit.cryptodroid.ExchangePaser.cryptopia.model.CryptopiaModel;
import jg.smartit.cryptodroid.model.CoinList;
import jg.smartit.cryptodroid.model.CoinModel;
import jg.smartit.cryptodroid.model.Order;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;
@Component
public class CryptopiaMapper {
    public CoinModel map(CryptopiaModel cryptopiaModel, CoinList coinList) {
        return new CoinModel(coinList, cryptopiaModel.getData().getBuy()
                .stream()
                .map(s -> new Order(s.getPrice(), s.getVolume()))
                .collect(Collectors.toList()),
                cryptopiaModel.getData().getSell()
                        .stream()
                        .map(s -> new Order(s.getPrice(), s.getVolume()))
                        .collect(Collectors.toList()), "CRYPTOPIA");
    }
}
