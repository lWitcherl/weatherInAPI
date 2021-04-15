package com.dut.education.entitys;

public class CityWeather {
    private int cityId;
    private String name;
    private float temp;
    private float feelsLike;
    private String time;
    private String weather;
    private int humidity;
    private int clouds;
    private double precipitation;
    private String icon;


    public CityWeather(WeatherFromApi weatherFromApi) {
        this.cityId = weatherFromApi.getId();
        this.name = weatherFromApi.getName();
        this.temp = weatherFromApi.getMain().getTemp();
        this.feelsLike = weatherFromApi.getMain().getFeels_like();
        this.clouds = weatherFromApi.getClouds().getAll();
        this.time = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm").format(new java.util.Date (weatherFromApi.getDt()* 1000L));
        this.weather = weatherFromApi.getWeather().get(0).getDescription();
        this.humidity = weatherFromApi.getMain().getHumidity();
        this.icon = weatherFromApi.getWeather().get(0).getIcon();
        this.precipitation += weatherFromApi.getRain() != null ? weatherFromApi.getRain().get1h() : 0;
        this.precipitation += weatherFromApi.getSnow() != null ? weatherFromApi.getSnow().get1h() : 0;
    }

    public CityWeather() {
    }

    public int getClouds() {
        return clouds;
    }

    public void setClouds(int clouds) {
        this.clouds = clouds;
    }

    public double getPrecipitation() {
        return precipitation;
    }

    public void setPrecipitation(double precipitation) {
        this.precipitation = precipitation;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getTemp() {
        return temp;
    }

    public void setTemp(float temp) {
        this.temp = temp;
    }

    public float getFeelsLike() {
        return feelsLike;
    }

    public void setFeelsLike(float feelsLike) {
        this.feelsLike = feelsLike;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    @Override
    public String toString() {
        return "CityWeather{" +
                "name='" + name + '\'' +
                ", temp=" + temp +
                ", feelsLike=" + feelsLike +
                ", time=" + time +
                ", weather='" + weather + '\'' +
                '}';
    }
}
