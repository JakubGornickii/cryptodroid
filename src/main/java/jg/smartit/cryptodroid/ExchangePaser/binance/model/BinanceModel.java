package jg.smartit.cryptodroid.ExchangePaser.binance.model;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class BinanceModel {



        @JsonProperty("bids")
        private List<List<Object>> bids = null;
        @JsonProperty("asks")
        private List<List<Object>> asks = null;

    public List<List<Object>> getBids() {
        return bids;
    }

    public void setBids(List<List<Object>> bids) {
        this.bids = bids;
    }

    public List<List<Object>> getAsks() {
        return asks;
    }

    public void setAsks(List<List<Object>> asks) {
        this.asks = asks;
    }
}
