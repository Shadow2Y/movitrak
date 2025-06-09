package com.movitrack.restapi;

import com.movitrack.model.client.DBClientConfig;
import com.movitrack.service.MovitrakService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class MoviTrakRestController {

    private final MovitrakService movitrakService;

    public MoviTrakRestController() {
        this.movitrakService = new MovitrakService();
    }

    @GetMapping("/data")
    public String index() {
        return movitrakService.getAll().toString();
    }

    @GetMapping("/refresh")
    public String refresh() {
        return movitrakService.refresh().toString();
    }

}
