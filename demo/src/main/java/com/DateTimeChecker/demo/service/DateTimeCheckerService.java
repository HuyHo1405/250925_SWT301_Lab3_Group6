package com.DateTimeChecker.demo.service;

import com.DateTimeChecker.demo.model.DateTimeModel;
import org.springframework.stereotype.Service;

/**
 * Implementation of DtcService for date-time validation operations.
 * This service provides concrete implementations for date validation,
 * leap year calculations, and month day calculations.
 */
@Service
public class DateTimeCheckerService implements DtcService {

    @Override
    public void process(DateTimeModel model) {
        try {
            // Validate input model
            if (model == null) {
                throw new IllegalArgumentException("Input data for is empty");
            }

            // Parse string inputs to integers
            int year = Integer.parseInt(model.getYear().trim());
            int month = Integer.parseInt(model.getMonth().trim());
            int day = Integer.parseInt(model.getDay().trim());

            // Format dd/MM/yyyy với padding
            String formattedDate = String.format("%02d/%02d/%04d", day, month, year);

            // Validate the date
            if (checkDate(year, month, day)) {
                model.setMessage(formattedDate + " is correct date time!");
            } else {
                model.setMessage(formattedDate + " is NOT correct date time!");
            }

        } catch (NumberFormatException e) {
            model.setMessage("Lỗi: Vui lòng nhập số hợp lệ cho ngày, tháng, năm!");
        } catch (IllegalArgumentException e) {
            model.setMessage("Lỗi: " + e.getMessage());
        } catch (Exception e) {
            model.setMessage("Đã xảy ra lỗi không xác định!");
        }
    }

    @Override
    public int daysInMonth(int year, int month) {
        // Validate month input
        if (month < 1 || month > 12) {
            throw new IllegalArgumentException("Input data for Month is out of range!");
        }

        // Calculate days based on month rules
        if (month == 2) {
            // February: 28 days normally, 29 in leap years
            return isLeapYear(year) ? 29 : 28;
        } else if (month == 4 || month == 6 || month == 9 || month == 11) {
            // April, June, September, November: 30 days
            return 30;
        } else {
            // January, March, May, July, August, October, December: 31 days
            return 31;
        }
    }

    @Override
    public boolean checkDate(int year, int month, int day) {
        try {
            // Basic validation
            if (year < 1000 || year > 3000) {
                return false;
            }

            if (month < 1 || month > 12) {
                return false;
            }

            if (day < 1 || day > 31) {
                return false;
            }

            // Check if day is within valid range for the month
            int maxDaysInMonth = daysInMonth(year, month);
            return day <= maxDaysInMonth;

        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Helper method to determine if a year is a leap year.
     * A leap year is divisible by 4, except for years divisible by 100,
     * unless they are also divisible by 400.
     *
     * @param year the year to check
     * @return true if the year is a leap year, false otherwise
     */
    private boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }
}