package jg.cryptodroid.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class CoinbeneModel {

    @JsonProperty("orderbook")
    private Orderbook orderbook;

    public Orderbook getOrderbook() {
        return orderbook;
    }

    public void setOrderbook(Orderbook orderbook) {
        this.orderbook = orderbook;
    }


    public static class Orderbook {

        @JsonProperty("bids")
        private List<Bid> bids = null;
        @JsonProperty("asks")
        private List<Ask> asks = null;

        public List<Bid> getBids() {
            return bids;
        }

        public void setBids(List<Bid> bids) {
            this.bids = bids;
        }

        public List<Ask> getAsks() {
            return asks;
        }

        public void setAsks(List<Ask> asks) {
            this.asks = asks;
        }

        public static class Bid {

            @JsonProperty("quantity")
            private Double quantity;
            @JsonProperty("price")
            private Double price;

            public Double getQuantity() {
                return quantity;
            }

            public void setQuantity(Double quantity) {
                this.quantity = quantity;
            }

            public Double getPrice() {
                return price;
            }

            public void setPrice(Double price) {
                this.price = price;
            }
        }

        public static class Ask {

            @JsonProperty("quantity")
            private Double quantity;
            @JsonProperty("price")
            private Double price;

            public Double getQuantity() {
                return quantity;
            }

            public void setQuantity(Double quantity) {
                this.quantity = quantity;
            }

            public Double getPrice() {
                return price;
            }

            public void setPrice(Double price) {
                this.price = price;
            }
        }

    }
}
