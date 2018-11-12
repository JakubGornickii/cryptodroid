package jg.cryptodroid.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class OkexModel {
    @JsonProperty("asks")
    private List<List<String>> asks = null;
    @JsonProperty("bids")
    private List<List<String>> bids = null;

    public List<List<String>> getAsks() {
        return asks;
    }

    public void setAsks(List<List<String>> asks) {
        this.asks = asks;
    }

    public List<List<String>> getBids() {
        return bids;
    }

    public void setBids(List<List<String>> bids) {
        this.bids = bids;
    }
}
