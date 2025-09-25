package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class DayInMonth {

    // Trang chủ - hiển thị form
    @GetMapping("/")
    public String showForm(Model model) {
        model.addAttribute("result", null);
        return "index";
    }

    // Xử lý khi submit form
    @PostMapping("/check")
    public String checkDate(@RequestParam("month") Integer month,
                            @RequestParam("year") Integer year,
                            Model model) {
        String message;
        int days = 0;

        // Validate input
        if (month == null || year == null) {
            message = "❌ Month and Year are required!";
        } else if (month < 1 || month > 12) {
            message = "❌ Month must be between 1 and 12!";
        } else if (year < 1000 || year > 3000) {
            message = "❌ Year must be between 1000 and 3000!";
        } else {
            // Xử lý số ngày trong tháng
            switch (month) {
                case 1: case 3: case 5: case 7: case 8: case 10: case 12:
                    days = 31;
                    message = "✅ Month " + month + " of year " + year + " has " + days + " days.";
                    break;
                case 4: case 6: case 9: case 11:
                    days = 30;
                    message = "✅ Month " + month + " of year " + year + " has " + days + " days.";
                    break;
                case 2:
                    days = isLeapYear(year) ? 29 : 28;
                    message = "✅ February of year " + year + " has " + days + " days.";
                    break;
                default:
                    message = "❌ Invalid month!";
            }
        }

        model.addAttribute("result", message);
        return "index"; // quay lại index.html và show kết quả
    }

    // Kiểm tra năm nhuận
    private boolean isLeapYear(int year) {
        if (year % 400 == 0) return true;
        if (year % 100 == 0) return false;
        return year % 4 == 0;
    }
}