package com.weather.android.db;

import org.litepal.crud.DataSupport;

/**
 * Created by 10032 on 2017/3/9.
 */

public class Province extends DataSupport {
    private int provinceCode;
    private String provinceName;
    private int id;

    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id=id;
    }
    public String getProvinceName(){
        return provinceName;
    }
    public void setProvinceName(String provinceName){
        this.provinceName=provinceName;
    }
    public int getProvinceCode(){
        return provinceCode;
    }
    public void setProvinceCode(int provinceCode){
        this.provinceCode=provinceCode;
    }
}
