package com.dut.education.controllers;

import com.dut.education.communications.WeatherCommunicator;
import com.dut.education.entitys.CityWeather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class GetFromWeatherApi {
    private WeatherCommunicator communicator;

    public GetFromWeatherApi() {
    }
    @Autowired
    public void setCommunicator(WeatherCommunicator communicator) {
        this.communicator = communicator;
    }

    @GetMapping("/cityweather/{var}")
    public CityWeather getCityWeather(@PathVariable String var){
        CityWeather cityWeather ;
        try {
            int id = Integer.valueOf(var);
            cityWeather = communicator.getCityWeather(id);
        }catch (Exception e){
            cityWeather = communicator.getCityWeather(var);
        }
        return cityWeather;
    }

    @GetMapping("/cityweather/{lon}/{lat}")
    public CityWeather getCityWeather(@PathVariable("lon") int lon,@PathVariable("lat") int lat){
        CityWeather cityWeather = communicator.getCityWeather(lon,lat);
        return cityWeather;
    }
}
