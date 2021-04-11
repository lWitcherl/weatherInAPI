package com.dut.education.communications;

import com.dut.education.entitys.CityWeather;
import com.dut.education.entitys.WeatherFromApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Properties;

@Component
public class WeatherCommunicatorImp implements WeatherCommunicator{

    private RestTemplate restTemplate;
    private Properties p = new Properties();
    private final String URL = "http://api.openweathermap.org/data/2.5/weather?appid=eb51488aa1d3ecd0d9291fc5d4ef9fd1&lang=ua&units=metric";

    WeatherCommunicatorImp() throws IOException {

    }

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public CityWeather getCityWeather(String cityName){
        WeatherFromApi weatherFromApi = restTemplate.getForObject(URL+"&q="+cityName, WeatherFromApi.class);
        CityWeather cityWeather = new CityWeather(weatherFromApi);
        return cityWeather;
    }

    public CityWeather getCityWeather(int id){
        WeatherFromApi weatherFromApi = restTemplate.getForObject(URL+"&id="+id, WeatherFromApi.class);
        CityWeather cityWeather = new CityWeather(weatherFromApi);
        return cityWeather;
    }

    public CityWeather getCityWeather(double lon,double lat){
        WeatherFromApi weatherFromApi = restTemplate.getForObject(URL+"&lon="+lon+"&lat="+lat, WeatherFromApi.class);
        CityWeather cityWeather = new CityWeather(weatherFromApi);
        return cityWeather;
    }

}
