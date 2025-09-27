package com.DateTimeChecker.demo.service;

import com.DateTimeChecker.demo.model.DateTimeModel;

/**
 * DtcService (Date Time Checker Service) Interface
 *
 * This service interface provides methods for date and time validation operations.
 * It includes functionality to determine the number of days in a given month
 * and validate whether a specific date is valid.
 *
 * @author Lab3 Group6
 * @version 1.0
 * @since 2025
 */
public interface DtcService {

    /**
     * Processes a DateTimeModel by validating the date and updating the model with results.
     * This method extracts the date components from the model, performs validation,
     * and sets an appropriate message in the model based on the validation result.
     *
     * @param model the DateTimeModel containing day, month, year fields to be processed.
     *              The model's message field will be updated with validation results.
     * @throws IllegalArgumentException if the model is null or contains invalid data
     */
    public void process(DateTimeModel model);

    /**
     * Calculates the number of days in a specific month of a given year.
     * Takes into account leap years for February calculations.
     *
     * @param year the year (e.g., 2023, 2024)
     * @param month the month (1-12, where 1 = January, 12 = December)
     * @return the number of days in the specified month and year
     * @throws IllegalArgumentException if month is not between 1-12
     */
    public int daysInMonth(int year, int month);

    /**
     * Validates whether a given date is a valid calendar date.
     * Checks if the day exists within the specified month and year,
     * considering leap years and varying month lengths.
     *
     * @param year the year (e.g., 2023, 2024)
     * @param month the month (1-12, where 1 = January, 12 = December)
     * @param day the day of the month (1-31, depending on the month)
     * @return true if the date is valid, false otherwise
     * @throws IllegalArgumentException if month is not between 1-12
     */
    public boolean checkDate(int year, int month, int day);
}