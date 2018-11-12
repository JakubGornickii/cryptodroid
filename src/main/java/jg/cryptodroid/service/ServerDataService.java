package jg.cryptodroid.service;

import jg.cryptodroid.enums.CoinList;
import jg.cryptodroid.inmemorydatabase.ExchangeBase;
import org.springframework.stereotype.Service;

@Service
public class ServerDataService {
    public static long timeStarted = System.currentTimeMillis();
    public static long queres;

    public static void queres(){
        queres++;

    }

    public long getActualData(){
        return System.currentTimeMillis();
    }
    public int getNumberOfCoins(){
        return CoinList.values().length;
    }
    public int getNumberofExchanges(){
        return ExchangeBase.EXCHANGES.size();
    }

    public long getQueresPerSecond() {
       return (queres/((System.currentTimeMillis()-timeStarted)/1000))*60;
    }
}
