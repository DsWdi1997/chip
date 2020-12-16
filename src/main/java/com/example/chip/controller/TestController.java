package com.example.chip.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


@RestController
public class TestController {
    @RequestMapping(value = "/demo", method = RequestMethod.POST)
    public String demo(HttpServletRequest req) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream()));
            StringBuffer sb = new StringBuffer();
            String s = null;
            while ((s = br.readLine()) != null) {
                sb.append(s);
            }
            JSONObject jsonObject = JSONObject.parseObject(sb.toString());
            String name = jsonObject.getString("name");
            String age = jsonObject.getString("age");
            System.out.println("name:" + name + " age:" + age);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "server response";
    }
}