package jg.smartit.cryptodroid.ExchangePaser.bitbay.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class BitbayModel {
    @JsonProperty("bids")
    private List<List<Double>> bids = null;
    @JsonProperty("asks")
    private List<List<Double>> asks = null;

    public List<List<Double>> getBids() {
        return bids;
    }

    public void setBids(List<List<Double>> bids) {
        this.bids = bids;
    }

    public List<List<Double>> getAsks() {
        return asks;
    }

    public void setAsks(List<List<Double>> asks) {
        this.asks = asks;
    }
}
