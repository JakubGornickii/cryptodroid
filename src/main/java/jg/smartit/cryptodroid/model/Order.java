package jg.smartit.cryptodroid.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Order {
    private double price;
    private double volume;
}
