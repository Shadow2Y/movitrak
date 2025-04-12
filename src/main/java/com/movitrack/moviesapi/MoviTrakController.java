package com.movitrack.moviesapi;

import org.springframework.web.bind.annotation.*;

@RestController
public class MoviTrakController {

    @RequestMapping("/")
    public String index() {
        return "HelloWorld.html";
    }
}