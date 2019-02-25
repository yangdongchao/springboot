package com.example.demo.domain;
import org.apache.commons.lang3.builder.ToStringBuilder;
import java.io.Serializable;

/**
 * @ClassName BaseDomain
 * @Description 基类
 * @Auther ydc
 * @Date 2019/1/7 20:02
 * @Version 1.0
 **/
public class BaseDomain implements Serializable
{

    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
