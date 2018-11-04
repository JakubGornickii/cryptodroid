package jg.cryptodroid.service;

import jg.cryptodroid.annotation.MarketType;
import jg.cryptodroid.mapper.ExchangeMapper;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;

public class CreateAnnotationClass {


    public ExchangeMapper findAnnotatedClasses(String annotation) {
        ClassPathScanningCandidateComponentProvider provider = createComponentScanner();
        for (BeanDefinition beanDef : provider.findCandidateComponents("jg.cryptodroid.mapper")) {

            try {
                Class<?> cl = Class.forName(beanDef.getBeanClassName());
                MarketType marketType = cl.getAnnotation(MarketType.class);
                if (marketType.name().equals(annotation)) {

                    Object o = Class.forName(beanDef.getBeanClassName()).getConstructor().newInstance();
                    ExchangeMapper exchangeMapper = (ExchangeMapper) o;

                    return exchangeMapper;
                }
            } catch (Exception e) {
                System.err.println(e.getMessage() +" Błąd");
                throw new RuntimeException();
            }
        }
        throw new RuntimeException();
    }

    private ClassPathScanningCandidateComponentProvider createComponentScanner() {
        ClassPathScanningCandidateComponentProvider provider
                = new ClassPathScanningCandidateComponentProvider(false);
        provider.addIncludeFilter(new AnnotationTypeFilter(MarketType.class));
        return provider;
    }
}