package cn.kayleh.trend.pojo;

/**
 * @Author: Kayleh
 * @Date: 2020/9/5 0:23
 */


public class Profit {

    String date;
    float value; // indexData.closePoint

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

}
