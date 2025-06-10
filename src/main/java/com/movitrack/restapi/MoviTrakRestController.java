package com.movitrack.restapi;

import com.movitrack.model.Item;
import com.movitrack.model.client.DBClientConfig;
import com.movitrack.service.MovitrakService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MoviTrakRestController {

    private final MovitrakService movitrakService;

    public MoviTrakRestController() {
        this.movitrakService = new MovitrakService();
    }

    @GetMapping("/data")
    public List<Item> index() {
        return movitrakService.getAll();
    }

    @GetMapping("/refresh")
    public List<Item> refresh() {
        return movitrakService.refresh();
    }

}
