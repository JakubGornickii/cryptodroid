package jg.smartit.cryptodroid.ExchangePaser.graviex.mapper;

import jg.smartit.cryptodroid.ExchangePaser.graviex.model.GraviexModel;
import jg.smartit.cryptodroid.model.CoinList;
import jg.smartit.cryptodroid.model.CoinModel;
import jg.smartit.cryptodroid.model.Order;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;
@Component
public class GraviexMapper {

    public CoinModel map(GraviexModel graviexModel, CoinList coinList) {
        return new CoinModel(coinList,
                graviexModel.getBids().stream()
        .map(s -> new Order(Double.parseDouble(s.getPrice()),Double.parseDouble(s.getVolume())))
                .collect(Collectors.toList()),
                graviexModel.getAsks().stream()
        .map(s -> new Order(Double.parseDouble(s.getPrice()),Double.parseDouble(s.getVolume())))
                .collect(Collectors.toList()), "GRAVIEX");
    }
}
