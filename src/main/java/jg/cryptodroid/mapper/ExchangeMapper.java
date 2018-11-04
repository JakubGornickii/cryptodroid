package jg.cryptodroid.mapper;

import jg.cryptodroid.enums.CoinList;
import jg.cryptodroid.model.CoinModel;

public interface ExchangeMapper{
    public CoinModel map(CoinList coinList, String queryUrl,String exchangeName);
}
