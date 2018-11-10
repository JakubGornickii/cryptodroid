package jg.cryptodroid.mapper;

import jg.cryptodroid.enums.CoinList;
import jg.cryptodroid.model.CoinModel;

import java.util.Optional;

public interface ExchangeMapper{
    public Optional<CoinModel> map(CoinList coinList, String queryUrl, String exchangeName);
}
