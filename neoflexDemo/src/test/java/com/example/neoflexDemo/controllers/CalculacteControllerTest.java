package com.example.neoflexDemo.controllers;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;


class CalculacteControllerTest {
    @Test
    void vacationPayWithTax()
    {
        var calculator = new CalculacteController();
        assertEquals(21750.0,calculator.vacationPayWithTax(300000,30));

    }

    @Test
    void countWeekend()
    {
        var calculator = new CalculacteController();
        assertEquals(9,calculator.countWeekend(LocalDate.of(2023,05,16),
                LocalDate.of(2023,06,16)));
    }



}
