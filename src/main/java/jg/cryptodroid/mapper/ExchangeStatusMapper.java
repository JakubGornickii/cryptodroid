package jg.cryptodroid.mapper;

import jg.cryptodroid.ExchangePaser.ExchangePaser;
import jg.cryptodroid.modelDTO.ExchangeStatusDTO;
import org.springframework.stereotype.Component;

@Component
public class ExchangeStatusMapper implements Map<ExchangeStatusDTO,ExchangePaser> {
    @Override
    public ExchangeStatusDTO map(ExchangePaser exchangePaser) {
        return new ExchangeStatusDTO(
                exchangePaser.getExchangeModel().getExchangeName().substring(0,1).toUpperCase()+
                exchangePaser.getExchangeModel().getExchangeName().substring(1).toLowerCase(),
                exchangePaser.isActive(),
                exchangePaser.getLastUpdate()
        );
    }
}
