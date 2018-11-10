package jg.cryptodroid.modelDTO;

import jg.cryptodroid.model.Order;

import java.util.List;

public class ExchangeCoinModelDTO {
    private String tag;
    private List<Order> bids;
    private List<Order> asks;

    public ExchangeCoinModelDTO(String tag, List<Order> bids, List<Order> asks) {
        this.tag = tag;
        this.bids = bids;
        this.asks = asks;
    }

    public String getTag() {
        return tag;
    }

    public List<Order> getBids() {
        return bids;
    }

    public List<Order> getAsks() {
        return asks;
    }
    
}
