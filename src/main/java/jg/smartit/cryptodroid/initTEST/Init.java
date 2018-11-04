package jg.smartit.cryptodroid.initTEST;

import jg.smartit.cryptodroid.coinsbase.CoinsBase;
import jg.smartit.cryptodroid.service.AnnotationScanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class Init {
    @Autowired
    private FindArbitrage findArbitrage;


    @PostConstruct
    public synchronized void init() {

        new AnnotationScanner().findAnnotatedClasses("jg.smartit.cryptodroid.ExchangePaser");


        while (true) {
            if (CoinsBase.MARKETS_COINS_DATA.size() > 10) {
                findArbitrage.generateList();
                findArbitrage.getArbitrage().stream().filter(s -> s.getEarnPercentage() > 0.1).forEach(System.out::println);
                System.out.println("--------------------------------------------------------------------------------------");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
