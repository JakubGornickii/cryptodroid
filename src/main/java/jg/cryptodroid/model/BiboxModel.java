package jg.cryptodroid.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class BiboxModel {
    @JsonProperty("result")
    private Result result;

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }


    public static class Result {

        @JsonProperty("asks")
        private List<Ask> asks = null;
        @JsonProperty("bids")
        private List<Bid> bids = null;

        public List<Ask> getAsks() {
            return asks;
        }

        public void setAsks(List<Ask> asks) {
            this.asks = asks;
        }

        public List<Bid> getBids() {
            return bids;
        }

        public void setBids(List<Bid> bids) {
            this.bids = bids;
        }
    }
    public static class Ask {

        @JsonProperty("volume")
        private String volume;
        @JsonProperty("price")
        private String price;

        public String getVolume() {
            return volume;
        }

        public void setVolume(String volume) {
            this.volume = volume;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }
    }
    public static class Bid {

        @JsonProperty("volume")
        private String volume;
        @JsonProperty("price")
        private String price;

        public String getVolume() {
            return volume;
        }

        public void setVolume(String volume) {
            this.volume = volume;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }
    }}
