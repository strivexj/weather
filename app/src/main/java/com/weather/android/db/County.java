package com.weather.android.db;

import org.litepal.crud.DataSupport;

/**
 * Created by 10032 on 2017/3/9.
 */

public class County extends DataSupport {
    private int id;
    private String countyName;
    private String weatherCode;
    private String cityId;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getCityId() {
        return cityId;
    }
    public void setCityId(String cityId) {
        this.cityId = cityId;
    }



    public String getCountyName() {
        return countyName;
    }
    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }





    public String getWeatherCode() {
        return weatherCode;
    }
    public void setWeatherCode(String weatherCode) {
        this.weatherCode = weatherCode;
    }

}