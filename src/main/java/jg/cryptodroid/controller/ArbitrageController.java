package jg.cryptodroid.controller;

import jg.cryptodroid.inmemorydatabase.ArbitrageBase;
import jg.cryptodroid.model.Arbitrage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/arbitrage")
public class ArbitrageController {
    @GetMapping(value = "/list")
    public ResponseEntity<List<Arbitrage>> getAll(){
        return new ResponseEntity<>(ArbitrageBase.ARBITRAGES, HttpStatus.OK);
    }
}
