package com.huhoot.auth;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.IntStream;

@RestController
@RequestMapping("api")
public class TestController {
    @GetMapping("/test")
    public List<Integer> test() {
        return IntStream.range(1, 100).mapToObj(e -> e).toList();

    }
}
