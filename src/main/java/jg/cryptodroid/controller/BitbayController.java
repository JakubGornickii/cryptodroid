package jg.cryptodroid.controller;

import jg.cryptodroid.coinsbase.CoinsBase;
import jg.cryptodroid.mapper.OrderListMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("api/bitbay")
public class BitbayController {

private OrderListMapper orderListMapper;

    public BitbayController(OrderListMapper orderListMapper) {
        this.orderListMapper = orderListMapper;
    }

    @GetMapping("/orderlist{coin}")
    public ResponseEntity<?> getOrdersList(@PathVariable(value = "coin") String coin){
       return CoinsBase.MARKETS_COINS_DATA.stream()
                .filter(s -> s.getMakretName().equals("BITBAY") && s.getTag().name().equals(coin.replace("BTC","")))
                .findFirst()
                .map(s -> new ResponseEntity<>(orderListMapper.map(s),HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
