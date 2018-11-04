package jg.cryptodroid.model;

import jg.cryptodroid.enums.CoinList;

import java.io.Serializable;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class CoinModel implements Serializable {
    private long timeCreated;
    private String makretName;
    private CoinList tag;
    private List<Order> bidOrders;
    private List<Order> askOrders;
    private final double ASK_PRICE;
    private final double BID_PRICE;

    public CoinModel(CoinList tag, List<Order> bidOrders, List<Order> askOrders,String marketName) {
        this.tag = tag;
        this.bidOrders = bidOrders;
        this.askOrders = askOrders;
        this.makretName = marketName;
        ASK_PRICE = this.askOrders.stream().map(Order::getPrice).min(Comparator.naturalOrder()).orElse(0.00);
        BID_PRICE = this.bidOrders.stream().map(Order::getPrice).max(Comparator.naturalOrder()).orElse(0.00);
        timeCreated = System.currentTimeMillis();
    }

    public long getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(long timeCreated) {
        this.timeCreated = timeCreated;
    }

    public String getMakretName() {
        return makretName;
    }

    public void setMakretName(String makretName) {
        this.makretName = makretName;
    }

    public CoinList getTag() {
        return tag;
    }

    public void setTag(CoinList tag) {
        this.tag = tag;
    }

    public List<Order> getBidOrders() {
        return bidOrders;
    }

    public void setBidOrders(List<Order> bidOrders) {
        this.bidOrders = bidOrders;
    }

    public List<Order> getAskOrders() {
        return askOrders;
    }

    public void setAskOrders(List<Order> askOrders) {
        this.askOrders = askOrders;
    }

    public double getASK_PRICE() {
        return ASK_PRICE;
    }

    public double getBID_PRICE() {
        return BID_PRICE;
    }

    @Override
    public String toString() {
        return "CoinModel{" +
                "timeCreated=" + timeCreated +
                ", makretName='" + makretName +
                ", tag=" + tag +
                ", ASK_PRICE=" + ASK_PRICE +
                ", BID_PRICE=" + BID_PRICE +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoinModel coinModel = (CoinModel) o;
        return timeCreated == coinModel.timeCreated &&
                Double.compare(coinModel.ASK_PRICE, ASK_PRICE) == 0 &&
                Double.compare(coinModel.BID_PRICE, BID_PRICE) == 0 &&
                Objects.equals(makretName, coinModel.makretName) &&
                tag == coinModel.tag &&
                Objects.equals(bidOrders, coinModel.bidOrders) &&
                Objects.equals(askOrders, coinModel.askOrders);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timeCreated, makretName, tag, bidOrders, askOrders, ASK_PRICE, BID_PRICE);
    }
}
