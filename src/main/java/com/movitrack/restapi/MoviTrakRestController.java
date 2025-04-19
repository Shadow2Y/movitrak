package com.movitrack.restapi;

import org.springframework.web.bind.annotation.*;

@RestController
public class MoviTrakRestController {

    @GetMapping("/data")
    public String index() {
        return "";
    }

}
