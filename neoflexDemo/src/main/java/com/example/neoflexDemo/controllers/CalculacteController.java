package com.example.neoflexDemo.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;



@Controller
public class CalculacteController {

    ArrayList<LocalDate> holidaysDate = new ArrayList<>()
            {
                {
                    add(LocalDate.of(2023,1,1));
                    add(LocalDate.of(2023,1,2));
                    add(LocalDate.of(2023,1,3));
                    add(LocalDate.of(2023,1,4));
                    add(LocalDate.of(2023,1,5));
                    add(LocalDate.of(2023,1,6));
                    add(LocalDate.of(2023,1,7));
                    add(LocalDate.of(2023,1,8));
                    add(LocalDate.of(2023,2,23));
                    add(LocalDate.of(2023,2,24));
                    add(LocalDate.of(2023,2,25));
                    add(LocalDate.of(2023,2,26));
                    add(LocalDate.of(2023,3,8));
                    add(LocalDate.of(2023,4,29));
                    add(LocalDate.of(2023,4,30));
                    add(LocalDate.of(2023,5,1));
                    add(LocalDate.of(2023,5,6));
                    add(LocalDate.of(2023,5,7));
                    add(LocalDate.of(2023,5,8));
                    add(LocalDate.of(2023,5,9));
                    add(LocalDate.of(2023,6,10));
                    add(LocalDate.of(2023,6,11));
                    add(LocalDate.of(2023,6,12));
                    add(LocalDate.of(2023,11,4));
                    add(LocalDate.of(2023,11,5));
                    add(LocalDate.of(2023,11,6));

                }
            };


    public double vacationPayWithTax(double averageSalary, double vacationDays)
    {

        double averageSalaryPerDay = ((averageSalary/12)/30);
        double vacationPay = ( averageSalaryPerDay * vacationDays );
        double vacationPayWithTax = vacationPay - ((vacationPay/100)*13);
        vacationPayWithTax = Math.ceil(vacationPayWithTax*100)/100;
        return vacationPayWithTax;

    }

    public int countWeekend(LocalDate startDate, LocalDate endDate)
    {
        int countWeekend = 0;
        if (startDate.isEqual(endDate))
            {
                return countWeekend;
            }
        while (startDate.isBefore(endDate))
            {
                if(DayOfWeek.SATURDAY.equals(startDate.getDayOfWeek())
                        || DayOfWeek.SUNDAY.equals(startDate.getDayOfWeek())
                        || holidaysDate.contains(startDate))
                {
                    countWeekend++;
                }
                startDate = startDate.plusDays(1);

            }
        return countWeekend;
    }

    @GetMapping("/")
    public String homePage(){

       return "homePage";

    }
    @GetMapping("/calculacte")
    public ResponseEntity<String> calculacte(
            @RequestParam(name = "averageSalary", required = false, defaultValue = "0") double averageSalary,
            @RequestParam(name = "vacationDays", required = false, defaultValue = "0") double vacationDays,
            @RequestParam(name = "vacationStart", required = false, defaultValue = "2022-12-31") LocalDate vacationStart


    )
    {

        double countPayDay = vacationDays-countWeekend(vacationStart,vacationStart.plusDays((long) vacationDays));
        if((vacationPayWithTax(averageSalary,vacationDays) != 0)& vacationStart.isAfter(LocalDate.of(2022,12,31)))
        {
            return ResponseEntity.ok("Сумма отпускных с учетом налогов = "
                    + vacationPayWithTax(averageSalary, countPayDay)
                    + " рублей.");
        }
        else return ResponseEntity.ok("Сделайте запрос на получение суммы отпускных, например: " +
                "http://localhost:8080/calculacte?averageSalary=450000&vacationDays=30&vacationStart=2023-05-16");

    }



}
