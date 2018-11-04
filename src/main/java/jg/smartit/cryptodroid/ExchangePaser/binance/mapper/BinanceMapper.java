package jg.smartit.cryptodroid.ExchangePaser.binance.mapper;

import jg.smartit.cryptodroid.ExchangePaser.binance.model.BinanceModel;
import jg.smartit.cryptodroid.model.CoinList;
import jg.smartit.cryptodroid.model.CoinModel;
import jg.smartit.cryptodroid.model.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
public class BinanceMapper {
    public CoinModel map(BinanceModel binanceModel, CoinList coinList){
        List<List<Object>> askList = binanceModel.getAsks();
        List<List<Object>> bidList = binanceModel.getBids();
        List<Order> askOrderList = askList.stream()
                .map(s -> new Order(Double.parseDouble((String) s.get(0)),Double.parseDouble((String) s.get(1))))
                .collect(Collectors.toList());
        List<Order> bidOrderList = bidList.stream()
                .map(s -> new Order(Double.parseDouble((String) s.get(0)),Double.parseDouble((String) s.get(1))))
                .collect(Collectors.toList());
        return new CoinModel(coinList,bidOrderList,askOrderList,"BINANCE");
    }
}
