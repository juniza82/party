package com.juniza82.party.controller;

import com.juniza82.party.domain.TestResponseDomain;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/hello")
    public String Hello() {
        System.out.println("Hello!!");
        return "hello";
    }

    @GetMapping("/hello/domain")
    public TestResponseDomain hellDomain(@RequestParam("name") String name, @RequestParam("amount") int amount) {
        System.out.println("/hello/domain!!");

        return new TestResponseDomain(name, amount);
    }

}
