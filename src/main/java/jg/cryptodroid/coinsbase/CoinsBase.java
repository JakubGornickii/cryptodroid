package jg.cryptodroid.coinsbase;


import jg.cryptodroid.model.CoinModel;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

public class CoinsBase {
   public static List<CoinModel> MARKETS_COINS_DATA = new CopyOnWriteArrayList<CoinModel>();
   public static Optional<CoinModel> findByExchangeNameAndTag(String exchangeName, String tag){
     return MARKETS_COINS_DATA.stream()
              .filter(s -> s.getMakretName().equals(exchangeName.toUpperCase()) && s.getTag().toString().equals(tag))
              .findFirst();
   }
}
