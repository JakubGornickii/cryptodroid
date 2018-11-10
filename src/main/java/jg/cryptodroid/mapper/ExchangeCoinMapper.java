package jg.cryptodroid.mapper;

import jg.cryptodroid.model.CoinModel;
import jg.cryptodroid.modelDTO.ExchangeCoinModelDTO;
import org.springframework.stereotype.Component;

@Component
public class ExchangeCoinMapper implements Map<ExchangeCoinModelDTO, CoinModel> {
    @Override
    public ExchangeCoinModelDTO map(CoinModel coinModel) {
        return new ExchangeCoinModelDTO(
                coinModel.getTag().name(),
                coinModel.getBidOrders(),
                coinModel.getAskOrders())
        ;
    }
}
