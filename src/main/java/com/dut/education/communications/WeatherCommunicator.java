package com.dut.education.communications;

import com.dut.education.entitys.CityWeather;

public interface WeatherCommunicator {
    public CityWeather getCityWeather(String cityName);

    public CityWeather getCityWeather(int id);

    public CityWeather getCityWeather(double lon, double lat);
}
