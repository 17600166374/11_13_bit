package io.jupeng.bitcoin.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/misc")
@CrossOrigin
public class MiscController {

    @GetMapping("/search")
    public String search(@RequestParam String keyword){

        if (keyword.matches("-?\\d+")){
            return "block detail url";
        }

        if (keyword.length() < 64){
            return "address info url";
        }

        if (keyword.startsWith("00000")){
            return "block detail url";
        }else {
            return "tx detail url";
        }

    }
}
