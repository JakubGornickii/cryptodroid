package jg.cryptodroid.initTEST;

import jg.cryptodroid.ExchangePaser.ExchangePaser;
import jg.cryptodroid.service.ArbitrageThread;
import jg.cryptodroid.service.ExchangeThread;
import jg.cryptodroid.service.TxtFileReader;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class Init {

    ArbitrageThread arbitrageThread;

    public Init(ArbitrageThread arbitrageThread) {
        this.arbitrageThread = arbitrageThread;
    }

    @PostConstruct
    public synchronized void init() {
       TxtFileReader txtFileReader = new TxtFileReader();
        String[] marketList = txtFileReader.readExchangeList();
        for (String s : marketList) {
            ExchangePaser exchangePaser = new ExchangePaser(s);
            ExchangeThread exchangeThread = new ExchangeThread(exchangePaser);
            Thread thread = new Thread(exchangeThread);
            thread.start();
        }

        Thread thread = new Thread(arbitrageThread);
        thread.start();
/*String url = "https://api.lbkex.com/v1/depth.do?symbol={LTAG}_btc&size=10&merge=1";
TestPaser testPaser = new TestPaser();
testPaser.test(url);*/
    }
}
