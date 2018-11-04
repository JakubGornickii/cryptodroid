package jg.smartit.cryptodroid.ExchangePaser.cryptopia.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class CryptopiaModel {
    @JsonProperty("Data")
    private Data data;

    public CryptopiaModel() {
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public static class Data {
        @JsonProperty("Buy")
        private List<Buy> buy = new ArrayList<>();
        @JsonProperty("Sell")
        private List<Sell> sell = new ArrayList<>();

        public Data(List<Buy> buy, List<Sell> sell) {
            this.buy = buy;
            this.sell = sell;
        }

        public Data() {
        }

        public List<Buy> getBuy() {
            return buy;
        }

        public void setBuy(List<Buy> buy) {
            this.buy = buy;
        }

        public List<Sell> getSell() {
            return sell;
        }

        public void setSell(List<Sell> sell) {
            this.sell = sell;
        }
    }
    public static class Buy {
        @JsonProperty("Price")
        private double price;
        @JsonProperty("Volume")
        private double volume;

        public Buy() {
        }


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
    public static class Sell {
        @JsonProperty("Price")
        private double price;
        @JsonProperty("Volume")
        private double volume;

        public Sell() {
        }


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
