package jg.cryptodroid.mapper;

import jg.cryptodroid.model.CoinModel;
import jg.cryptodroid.modelDTO.OrderListDto;
import org.springframework.stereotype.Component;

@Component
public class OrderListMapper implements Map<OrderListDto, CoinModel> {

    @Override
    public OrderListDto map(CoinModel coinModel) {
        return new OrderListDto(coinModel.getTag().name(),
                coinModel.getMakretName(),
                coinModel.getAskOrders(),
                coinModel.getBidOrders(),
                coinModel.getTimeCreated());
    }
}
