package com.riding.springboot.controller;

import com.riding.springboot.domain.Hello;
import com.riding.springboot.domain.Param;
import com.riding.springboot.reponse.JSONResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "hello")
public class HelloController {
    @GetMapping()
    public Hello get() {
        return new Hello(0, "Hello Spring Boot!!");
    }

    @GetMapping("args")
    public Hello getArgs(
            @RequestParam(value = "msg")String msg,
            @RequestParam(value = "msg2")String msg2) {
        Hello hello = new Hello(0, msg);
        hello.setMsg(hello.getMsg() + ", " + msg2);

        return hello;
    }

    @GetMapping("argsDefault")
    public Hello getArgsDefault(
            @RequestParam(value = "msg")String msg,
            @RequestParam(value = "msg2", required = false, defaultValue = "msg2Default")String msg2) {
        Hello hello = new Hello(0, msg);
        hello.setMsg(hello.getMsg() + ", " + msg2);

        return hello;
    }

    @GetMapping("jsonResponse")
    public JSONResponse<Hello> getJsonResponse(
            @RequestParam(value = "msg")String msg){
        Hello hello = new Hello(0,msg);

        JSONResponse<Hello> response = new JSONResponse<Hello>();
        response.setCode(1);
        response.setMsg("응답코드가 1입니다.");
        response.setData(hello);

        return response;
    }

    @PostMapping
    public Hello post(@RequestBody Param param){
        String msg = "Data1 : " + param.getData1() + ", Data2 : " + param.getData2();
        Hello hello = new Hello(0, msg);
        return hello;
    }

    @PutMapping
    public Hello put(@RequestParam(value = "param1")String param1){
        Hello hello = new Hello(0,"Put Request : " + param1);
        return hello;
    }
}
