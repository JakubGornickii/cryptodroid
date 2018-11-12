package jg.cryptodroid.mapper;

import jg.cryptodroid.ExchangePaser.ExchangePaser;
import jg.cryptodroid.inmemorydatabase.CoinsBase;
import jg.cryptodroid.modelDTO.ExchangeCoinModelDTO;
import jg.cryptodroid.modelDTO.ExchangeDetailsDTO;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class ExchangeDetailsMapper implements Map<ExchangeDetailsDTO, ExchangePaser> {

    private ExchangeCoinMapper exchangeCoinMapper;

    public ExchangeDetailsMapper(ExchangeCoinMapper exchangeCoinMapper) {
        this.exchangeCoinMapper = exchangeCoinMapper;
    }

    @Override
    public ExchangeDetailsDTO map(ExchangePaser exchangePaser) {
        List<ExchangeCoinModelDTO> coinModels = new LinkedList<>();
        exchangePaser.getExchangeModel().getCoinsList()
                .forEach(s -> (CoinsBase.findByExchangeNameAndTag(exchangePaser.getExchangeModel().getExchangeName(), s.name()))
                        .map(e -> exchangeCoinMapper.map(e))
                        .ifPresent(coinModels::add));
        return new ExchangeDetailsDTO(
                exchangePaser.getExchangeModel().getExchangeName().substring(0, 1)
                        + exchangePaser.getExchangeModel().getExchangeName().substring(1).toLowerCase(),
                coinModels, exchangePaser.isActive(), exchangePaser.getLastUpdate(),exchangePaser.getExchangeModel().getMarketFees());
    }
}
