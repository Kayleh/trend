package cn.kayleh.trend.pojo;

import java.io.Serializable;

/**
 * @Author: Kayleh
 * @Date: 2020/8/31 13:03
 */
public class Index implements Serializable
{
    String code;
    String name;

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}
