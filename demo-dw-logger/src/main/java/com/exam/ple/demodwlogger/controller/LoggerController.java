package com.exam.ple.demodwlogger.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @date 2019-10-18 - 21:21
 */

@Controller
public class LoggerController {

    @PostMapping("/log")
    @ResponseBody
    public String log(@RequestParam("logStr") String logStr) {
        System.out.println(logStr);
        return "succeSS";
    }
}
