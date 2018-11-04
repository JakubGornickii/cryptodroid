package jg.smartit.cryptodroid.ExchangePaser.bitbay.mapper;

import jg.smartit.cryptodroid.ExchangePaser.bitbay.model.BitbayModel;
import jg.smartit.cryptodroid.model.CoinList;
import jg.smartit.cryptodroid.model.CoinModel;
import jg.smartit.cryptodroid.model.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
public class BitbayMapper {
public CoinModel map(BitbayModel bitbayModel, CoinList coinList){
    List<List<Double>> askList = bitbayModel.getAsks();
    List<List<Double>> bidList = bitbayModel.getBids();
    List<Order> askOrderList = askList.stream()
            .map(s -> new Order(s.get(0),s.get(1)))
            .collect(Collectors.toList());
    List<Order> bidOrderList = bidList.stream()
            .map(s -> new Order(s.get(0),s.get(1)))
            .collect(Collectors.toList());
    return new CoinModel(coinList,bidOrderList,askOrderList,"BITBAY");
}
}
