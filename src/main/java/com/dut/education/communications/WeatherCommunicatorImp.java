package com.dut.education.communications;

import com.dut.education.entitys.CityWeather;
import com.dut.education.entitys.WeatherFromApi;
import com.dut.education.entitys.exception.NoSuchCityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Component
public class WeatherCommunicatorImp implements WeatherCommunicator{

    private RestTemplate restTemplate;
    private Properties p = new Properties();
    private final String URL ;

    WeatherCommunicatorImp(RestTemplate restTemplate) throws IOException {
        this.restTemplate = restTemplate;
        String part = WeatherCommunicatorImp.class.getClassLoader().getResource("setting.properties").getPath();
        p.load(new FileInputStream(part));
        URL = p.getProperty("URL");
    }


    public CityWeather getCityWeather(String cityName) throws Throwable{
        WeatherFromApi weatherFromApi;
        try{
            weatherFromApi = restTemplate.getForObject(URL+"&q="+cityName, WeatherFromApi.class);
        }catch (HttpStatusCodeException e){
            if(e.getStatusCode()== HttpStatus.NOT_FOUND) throw new NoSuchCityException("not found city :"+cityName);
            else throw e;
        }
        CityWeather cityWeather = new CityWeather(weatherFromApi);
        return cityWeather;
    }

    public CityWeather getCityWeather(int id)throws Throwable{
        WeatherFromApi weatherFromApi;
        try{
            weatherFromApi = restTemplate.getForObject(URL+"&id="+id, WeatherFromApi.class);
        }catch (HttpStatusCodeException e){
            if(e.getStatusCode()== HttpStatus.NOT_FOUND) throw new NoSuchCityException("not found city by id :"+id);
            else throw e;
        }
        CityWeather cityWeather = new CityWeather(weatherFromApi);
        return cityWeather;
    }

    public CityWeather getCityWeather(double lon,double lat)throws Throwable{
        WeatherFromApi weatherFromApi;
        try{
            weatherFromApi = restTemplate.getForObject(URL+"&lon="+lon+"&lat="+lat, WeatherFromApi.class);
        }catch (HttpStatusCodeException e){
            if(e.getStatusCode()== HttpStatus.NOT_FOUND) throw new NoSuchCityException("not found city by cord:"+lon+" "+lat);
            else throw e;
        }
        CityWeather cityWeather = new CityWeather(weatherFromApi);
        return cityWeather;
    }

}
