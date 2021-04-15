package com.dut.education.communications;

import com.dut.education.entitys.CityWeather;

public interface WeatherCommunicator {
    public CityWeather getCityWeather(String cityName) throws Throwable;

    public CityWeather getCityWeather(int id) throws Throwable;

    public CityWeather getCityWeather(double lon, double lat) throws Throwable;
}
