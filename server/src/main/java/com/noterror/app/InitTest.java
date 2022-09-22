package com.noterror.app;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@CrossOrigin
public class InitTest {
    @GetMapping("/init/test")
    public ResponseEntity<String> helloWorld() {
        String data = "hello world";
        return ResponseEntity.ok(data);
    }
}
