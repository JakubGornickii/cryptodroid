package jg.cryptodroid.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class BittrexModel {
    @JsonProperty("result")
    private Result result;

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public static class Result{
        @JsonProperty("buy")
        private List<Buy> buy = null;
        @JsonProperty("sell")
        private List<Sell> sell = null;

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

        public static class Buy{
         @JsonProperty("Quantity")
         private Double quantity;
         @JsonProperty("Rate")
         private Double rate;

         public Double getQuantity() {
             return quantity;
         }

         public void setQuantity(Double quantity) {
             this.quantity = quantity;
         }

         public Double getRate() {
             return rate;
         }

         public void setRate(Double rate) {
             this.rate = rate;
         }
     }
     public static class Sell{
         @JsonProperty("Quantity")
         private Double quantity;
         @JsonProperty("Rate")
         private Double rate;

         public Double getQuantity() {
             return quantity;
         }

         public void setQuantity(Double quantity) {
             this.quantity = quantity;
         }

         public Double getRate() {
             return rate;
         }

         public void setRate(Double rate) {
             this.rate = rate;
         }
     }
    }

}
