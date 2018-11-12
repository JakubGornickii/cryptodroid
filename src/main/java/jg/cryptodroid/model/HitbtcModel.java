package jg.cryptodroid.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class HitbtcModel {
    @JsonProperty("ask")
    private List<Ask> ask = null;
    @JsonProperty("bid")
    private List<Bid> bid = null;

    public List<Ask> getAsk() {
        return ask;
    }

    public void setAsk(List<Ask> ask) {
        this.ask = ask;
    }

    public List<Bid> getBid() {
        return bid;
    }

    public void setBid(List<Bid> bid) {
        this.bid = bid;
    }

    public static class Bid {

        @JsonProperty("price")
        private String price;
        @JsonProperty("size")
        private String size;

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getSize() {
            return size;
        }

        public void setSize(String size) {
            this.size = size;
        }
    }

    public static class Ask {

        @JsonProperty("price")
        private String price;
        @JsonProperty("size")
        private String size;

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getSize() {
            return size;
        }

        public void setSize(String size) {
            this.size = size;
        }
    }
}