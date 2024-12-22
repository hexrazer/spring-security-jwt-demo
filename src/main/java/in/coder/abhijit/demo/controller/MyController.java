package in.coder.abhijit.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
class MyController{

    @GetMapping("/{name}")
    public String printName(@PathVariable String name){
        return "Welcome to spring boot security "+name;
    }
}