package com.example.demo2.com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private Producer producer;


    @RequestMapping("/send")
    public void test() throws InterruptedException {
        producer.sendMsg("sif");
    }
}
