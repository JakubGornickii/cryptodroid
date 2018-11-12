package jg.cryptodroid.controller;

import jg.cryptodroid.service.ServerDataService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/server")
public class ServerDataController {

    private ServerDataService serverDataService;

    public ServerDataController(ServerDataService serverDataService) {
        this.serverDataService = serverDataService;
    }

    @GetMapping("/time")
    @ResponseStatus(value = HttpStatus.OK)
    public long getActualServerTime(){
        return serverDataService.getActualData();
    }
    @GetMapping("/coins")
    @ResponseStatus(value = HttpStatus.OK)
    public int getNumberOfCoins() {
        return serverDataService.getNumberOfCoins();
    }

    @GetMapping("/exchanges")
    @ResponseStatus(value = HttpStatus.OK)
    public int getNumberofExchanges(){
        return serverDataService.getNumberofExchanges();
    }

    @GetMapping("/queres")
    @ResponseStatus(value = HttpStatus.OK)
    public long getQueresPerSecond(){
        return serverDataService.getQueresPerSecond();
    }
}
