package com.kgisl.aidemo.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kgisl.aidemo.Services.HelloService;

@RestController
@RequestMapping("/chat")
public class HelloController {

   private final HelloService ollamaService;

    public HelloController(HelloService ollamaService) {
        this.ollamaService = ollamaService;
    }

    @PostMapping("/query")
    public ResponseEntity<String> queryOllama(@RequestBody String input) {
        return ResponseEntity.ok(ollamaService.getResponse(input));
    }
    
}
