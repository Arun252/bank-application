package com.areteans.bankapplication.controller;


import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


import java.util.Map;

@RestController
@RequestMapping(path = "test")
public class TestController {


    @GetMapping(path = "/data")
    public String getDummydata(@RequestParam(value = "name" , required = false) String name,
                                @RequestParam(value = "age", required = false) Integer age) {
        return name!=null && age!=null ? name.toUpperCase() + " : " + age : "Empty input";

    }
    @GetMapping(path ="data/{age}")
    public String getfromPV(@PathVariable("age") Integer age) {
        return  String.valueOf(age);
    }

    @PostMapping(path = "data", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String getDummyData(@RequestBody Map<String,String> input) {
        String name = input.get("name");
        Integer age= Integer.valueOf(input.get("age"));
        return name!=null && age!=null ? name.toUpperCase() + " : " + age : "Empty input";
    }

    @PutMapping (path = "data", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String getDData(@RequestBody Map<String,String> input) {
        String name = input.get("name");
        Integer age= Integer.valueOf(input.get("age"));
        return name!=null && age!=null ? name.toUpperCase() + " : " + age : "Empty input";
    }
}


