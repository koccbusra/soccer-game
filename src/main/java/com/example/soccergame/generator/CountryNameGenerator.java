package com.example.soccergame.generator;

import java.util.Locale;
import java.util.Random;

public class CountryNameGenerator {
    public static String getRandomCountryName()
    {
        return new Locale("",Locale.getISOCountries()[new Random().nextInt(249)]).getDisplayName().toUpperCase();
    }
}