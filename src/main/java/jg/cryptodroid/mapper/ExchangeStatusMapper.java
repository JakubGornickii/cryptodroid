package jg.cryptodroid.mapper;

import jg.cryptodroid.ExchangePaser.ExchangePaser;
import jg.cryptodroid.modelDTO.ExchangeStatusDTO;
import org.springframework.stereotype.Component;

@Component
public class ExchangeStatusMapper implements Map<ExchangeStatusDTO,ExchangePaser> {
    @Override
    public ExchangeStatusDTO map(ExchangePaser exchangePaser) {
        return new ExchangeStatusDTO(
                exchangePaser.isActive(),
                exchangePaser.getLastUpdate()
        );
    }
}
