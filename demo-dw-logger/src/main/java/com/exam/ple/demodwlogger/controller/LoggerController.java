package com.exam.ple.demodwlogger.controller;

import c.e.p.gm.commons.constants.GmConstants;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @date 2019-10-18 - 21:21
 */

@Controller
@Slf4j
public class LoggerController {

    @Autowired
    KafkaTemplate<String,String> kafkaTemplate; // idea spring say err -- but no err here. it can run.

    @PostMapping(GmConstants.postPath)
    @ResponseBody
    public String log(@RequestParam(GmConstants
            .UPLOAD_OUT_REQUEST_IN_logStr) String logStr) {

        JSONObject jsonObject = JSON.parseObject(logStr);
        jsonObject.put("ts",System.currentTimeMillis());


        //log.debug(logStr);
        log.info(logStr);
        //log.error(logStr);


        if("startup".equals(jsonObject.get("type")  )  ){
            kafkaTemplate.send(GmConstants.KAFKA_STARTUP,jsonObject.toJSONString());
        }else{
            kafkaTemplate.send(GmConstants.KAFKA_EVENT,jsonObject.toJSONString());
        }




        System.out.println(logStr);
        return "succeSS";
    }
}
