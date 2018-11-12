package jg.cryptodroid.service;

import jg.cryptodroid.inmemorydatabase.CoinsBase;
import jg.cryptodroid.enums.CoinList;
import jg.cryptodroid.model.Arbitrage;
import jg.cryptodroid.model.CoinModel;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class FindArbitrage {


    Map<CoinList, List<CoinModel>> coinsSortedByTag;

    public void generateList() {
        coinsSortedByTag = new HashMap<>();
        for (CoinModel coinModel : CoinsBase.MARKETS_COINS_DATA) {
            if (coinsSortedByTag.containsKey(coinModel.getTag())) {
                coinsSortedByTag.get(coinModel.getTag()).add(coinModel);
            } else {
                List<CoinModel> temp = new ArrayList<>();
                temp.add(coinModel);
                coinsSortedByTag.put(coinModel.getTag(), temp);
            }

        }
    }

    public List<Arbitrage> getArbitrage() {
        List<Arbitrage> arbitrages = new LinkedList<>();
        for (List<CoinModel> value : coinsSortedByTag.values()) {
            if (value.size() >= 2) {
                for (int i = 0; i < value.size(); i++) {
                    for (int j = i; j < value.size(); j++) {
                        if ((!value.get(i).getMakretName().equals(value.get(j).getMakretName())) && value.get(i).getASK_PRICE() < value.get(j).getBID_PRICE()) {
                            arbitrages.add(
                                    new Arbitrage(value.get(i).getMakretName(),
                                            value.get(j).getMakretName(),
                                            value.get(i).getTag().name(),
                                            value.get(i).getASK_PRICE(),
                                            value.get(j).getBID_PRICE(),
                                            value.get(i).getASK_VOLUME(),
                                            value.get(j).getBID_VOLUME(),
                                            (value.get(i).getTimeCreated() >= value.get(j).getTimeCreated()) ? value.get(i).getTimeCreated() : value.get(j).getTimeCreated())
                            );

                        }
                        if ((!value.get(i).getMakretName().equals(value.get(j).getMakretName())) && value.get(j).getASK_PRICE() < value.get(i).getBID_PRICE()) {
                            arbitrages.add(new Arbitrage(value.get(j).getMakretName(),
                                    value.get(i).getMakretName(),
                                    value.get(j).getTag().name(),
                                    value.get(j).getASK_PRICE(),
                                    value.get(i).getBID_PRICE(),
                                    value.get(j).getASK_VOLUME(),
                                    value.get(i).getBID_VOLUME(),
                                    (value.get(i).getTimeCreated() >= value.get(j).getTimeCreated()) ? value.get(i).getTimeCreated() : value.get(j).getTimeCreated())
                            );
                        }
                    }
                }
            }
        }
        return arbitrages;
    }

}
