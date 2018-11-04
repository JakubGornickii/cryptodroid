package jg.smartit.cryptodroid.service;

import jg.smartit.cryptodroid.ExchangePaser.ExchangePaser;
import jg.smartit.cryptodroid.annotation.MarketPaser;
import jg.smartit.cryptodroid.initTEST.MarketThread;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;

public class AnnotationScanner {


    public void findAnnotatedClasses(String scanPackage) {
        ClassPathScanningCandidateComponentProvider provider = createComponentScanner();
        for (BeanDefinition beanDef : provider.findCandidateComponents(scanPackage)) {
            createThreads(beanDef);
        }
    }

    private ClassPathScanningCandidateComponentProvider createComponentScanner() {
        ClassPathScanningCandidateComponentProvider provider
                = new ClassPathScanningCandidateComponentProvider(false);
        provider.addIncludeFilter(new AnnotationTypeFilter(MarketPaser.class));
        return provider;
    }

    private void createThreads(BeanDefinition beanDef) {
        try {
            Class<?> cl = Class.forName(beanDef.getBeanClassName());
            MarketPaser marketPaser = cl.getAnnotation(MarketPaser.class);
            Object o = Class.forName(beanDef.getBeanClassName()).getConstructor().newInstance();

            ExchangePaser exchangePaser = (ExchangePaser)o;
            System.out.println(exchangePaser.getExchangeModel().getMarketName());
            MarketThread marketThread = new MarketThread(exchangePaser);
            Thread thread = new Thread(marketThread);
            thread.start();



        } catch (Exception e) {
            System.err.println(e.getMessage() +" Błąd");
        }
    }
}