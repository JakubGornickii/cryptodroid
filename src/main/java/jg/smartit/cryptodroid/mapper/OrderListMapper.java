package jg.smartit.cryptodroid.mapper;

import jg.smartit.cryptodroid.model.CoinModel;
import jg.smartit.cryptodroid.modelDTO.OrderListDto;
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
