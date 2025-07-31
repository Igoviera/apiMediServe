package com.MediServe.apiMediServe.utils;

import java.time.DayOfWeek;

public class DateUtils {
    public static String getDayLabel(DayOfWeek dayOfWeek) {
        switch (dayOfWeek) {
            case MONDAY: return "Segunda-feira";
            case TUESDAY: return "Terça-feira";
            case WEDNESDAY: return "Quarta-feira";
            case THURSDAY: return "Quinta-feira";
            case FRIDAY: return "Sexta-feira";
            case SATURDAY: return "Sábado";
            case SUNDAY: return "Domingo";
            default: return "";
        }
    }
}
