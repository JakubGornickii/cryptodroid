package jg.cryptodroid.controller;

import jg.cryptodroid.exchangebase.ExchangeBase;
import jg.cryptodroid.mapper.ExchangeDetailsMapper;
import jg.cryptodroid.mapper.ExchangeStatusMapper;
import jg.cryptodroid.modelDTO.ExchangeDetailsDTO;
import jg.cryptodroid.modelDTO.ExchangeStatusDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/api/exchange")
public class ExchangeController {

    private ExchangeStatusMapper exchangeStatusMapper;
    private ExchangeDetailsMapper exchangeDetailsMapper;


    public ExchangeController(ExchangeStatusMapper exchangeStatusMapper, ExchangeDetailsMapper exchangeDetailsMapper) {
        this.exchangeStatusMapper = exchangeStatusMapper;
        this.exchangeDetailsMapper = exchangeDetailsMapper;
    }

    @GetMapping("/list")

    public ResponseEntity<List<String>> getExchangeList() {
        if (ExchangeBase.EXCHANGES.size() == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(ExchangeBase.EXCHANGES.stream()
                    .map(s -> s.getExchangeModel().getExchangeName().toLowerCase())
                    .map(s -> s.substring(0, 1).toUpperCase() + s.substring(1))
                    .collect(Collectors.toList()), HttpStatus.OK);
        }
    }

    @GetMapping("/status/{name}")
    public ResponseEntity<ExchangeStatusDTO> getExchangeStatus(@PathVariable(value = "name") String name) {
        return ExchangeBase.EXCHANGES.stream()
                .filter(s -> s.getExchangeModel().getExchangeName().equals(name.toUpperCase()))
                .findAny()
                .map(s -> new ResponseEntity<>(exchangeStatusMapper.map(s), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/status")
    public ResponseEntity<List<ExchangeStatusDTO>> getExchangesStatus() {
        if (ExchangeBase.EXCHANGES.size() == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(ExchangeBase.EXCHANGES.stream()
                    .map(s -> exchangeStatusMapper.map(s))
                    .collect(Collectors.toList()), HttpStatus.OK);
        }
    }

    @GetMapping("/coins{name}")
    public ResponseEntity<List<String>> getExchangeCoins(@PathVariable(value = "name") String name) {
        return ExchangeBase.EXCHANGES
                .stream()
                .filter(s -> s.getExchangeModel().getExchangeName().equals(name.toUpperCase()))
                .map(s -> s.getExchangeModel().getCoinsList()
                        .stream()
                        .map(Enum::name)
                        .collect(Collectors.toList()))
                .findFirst()
                .map(s -> new ResponseEntity<>(s, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/details{name}")
    public ResponseEntity<ExchangeDetailsDTO> getExchangeDetails(@PathVariable(value = "name") String name) {
        return ExchangeBase.EXCHANGES.stream()

                .filter(s -> s.getExchangeModel().getExchangeName().equals(name.toUpperCase()))
                .findAny()
                .map(s -> new ResponseEntity<>(exchangeDetailsMapper.map(s), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
