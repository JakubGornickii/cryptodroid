package jg.cryptodroid.modelDTO;

import jg.cryptodroid.model.Order;

import java.util.List;

public class OrderListDto {
    private long timestamp;
    private String coin;
    private String market;
    private List<Order> ask;
    private List<Order> bid;

    public OrderListDto(String coin, String market, List<Order> ask, List<Order> bid, long timestamp) {
        this.coin = coin;
        this.market = market;
        this.ask = ask;
        this.bid = bid;
        this.timestamp = timestamp;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getCoin() {
        return coin;
    }

    public String getMarket() {
        return market;
    }

    public List<Order> getAsk() {
        return ask;
    }

    public List<Order> getBid() {
        return bid;
    }
}
