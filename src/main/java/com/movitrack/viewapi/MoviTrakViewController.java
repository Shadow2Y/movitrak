package com.movitrack.viewapi;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class MoviTrakViewController {

    @RequestMapping("/")
    public String index() {
        return "index";
    }

}