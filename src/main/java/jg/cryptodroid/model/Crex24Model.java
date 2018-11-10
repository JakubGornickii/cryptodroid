package jg.cryptodroid.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Crex24Model {

    @JsonProperty("buyLevels")
    private List<BuyLevel> buyLevels = null;
    @JsonProperty("sellLevels")
    private List<SellLevel> sellLevels = null;

    public List<BuyLevel> getBuyLevels() {
        return buyLevels;
    }

    public void setBuyLevels(List<BuyLevel> buyLevels) {
        this.buyLevels = buyLevels;
    }

    public List<SellLevel> getSellLevels() {
        return sellLevels;
    }

    public void setSellLevels(List<SellLevel> sellLevels) {
        this.sellLevels = sellLevels;
    }

    public static class BuyLevel{
        @JsonProperty("price")
        private double price;
        @JsonProperty("volume")
        private double volume;

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public double getVolume() {
            return volume;
        }

        public void setVolume(double volume) {
            this.volume = volume;
        }
    }

    public static class SellLevel{
        @JsonProperty("price")
        private double price;
        @JsonProperty("volume")
        private double volume;

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public double getVolume() {
            return volume;
        }

        public void setVolume(double volume) {
            this.volume = volume;
        }
    }

}
