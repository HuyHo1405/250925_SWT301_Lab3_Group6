package com.DateTimeChecker.demo.controller;

import com.DateTimeChecker.demo.model.DateTimeModel;
import com.DateTimeChecker.demo.service.DtcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DtcController {
    @Autowired
    private DtcService dateTimeService;

    @GetMapping("/")
    public String showForm() {
        return "index"; // Thymeleaf template
    }

    // Nhận JSON từ form JS, trả Model về
    @PostMapping("/check")
    @ResponseBody
    public DateTimeModel checkDateTime(@RequestBody DateTimeModel model) {
        dateTimeService.process(model); // service mở model, tính, thêm message
        return model; // trả về JSON hoặc Model
    }
}
