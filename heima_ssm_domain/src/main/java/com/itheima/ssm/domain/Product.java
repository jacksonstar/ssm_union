package com.itheima.ssm.domain;

import com.itheima.ssm.utils.Datautils;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Product {
    private String id; // 主键
    private String productNum; // 编号 唯一   1
    private String productName; // 名称   1
    private String cityName; // 出发城市 1

//    将java格式与数据库需要的格式相互转化
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date departureTime; // 出发时间  1

//    如果不用这个，直接显示date类型，则会显示DAC SAT。。。各种类型
//    所以得转化成你锁需要的类型  添加工具类实现
    private String departureTimeStr; //出发时间字符串
    private double productPrice; // 产品价格  1
    private String productDesc; // 产品描述   1
    private Integer productStatus; // 状态 0 关闭 1 开启   1
    private String productStatusStr; //状态字符串





    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductNum() {
        return productNum;
    }

    public void setProductNum(String productNum) {
        this.productNum = productNum;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    public String getDepartureTimeStr() throws Exception {
        if (departureTime != null){
            departureTimeStr = Datautils.Date2String(departureTime);
        }

        return departureTimeStr;
    }

    public void setDepartureTimeStr(String departureTimeStr) throws Exception {
        this.departureTimeStr = departureTimeStr;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public Integer getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(Integer productStatus) {
        this.productStatus = productStatus;
    }

    public String getProductStatusStr() {
        if (productStatus != null){
            if (productStatus == 0){
                productStatusStr = "关闭";
            }

            if (productStatus == 1) {
                productStatusStr = "开启";
            }
        }
        return productStatusStr;
    }

    public void setProductStatusStr(String productStatusStr) {
        this.productStatusStr = productStatusStr;
    }
}
