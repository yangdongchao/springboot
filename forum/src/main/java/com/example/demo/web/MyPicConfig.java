package com.example.demo.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName MyPicConfig
 * @Description TODO
 * @Auther ydc
 * @Date 2019/2/14 17:36
 * @Version 1.0
 **/
//新增加一个类用来添加虚拟路径映射
@Configuration
public class MyPicConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        System.out.println("C:\\Program Files\\apache-tomcat-9.0.13\\apache-tomcat-9.0.13\\webapps\\springboot\\WEB-INF\\classes\\static\\userBg\\");
        //registry.addResourceHandler("/userImage/**").addResourceLocations("file:C:\\Users\\Administrator\\Documents\\GitHub\\forum\\forum\\src\\main\\resources\\static\\userImage\\");
        registry.addResourceHandler("/userImage/**").addResourceLocations("file:C:\\Program Files\\apache-tomcat-9.0.13\\apache-tomcat-9.0.13\\webapps\\springboot\\WEB-INF\\classes\\static\\userImage\\");
        //registry.addResourceHandler("/userBg/**").addResourceLocations("file:C:\\Users\\Administrator\\Documents\\GitHub\\forum\\forum\\src\\main\\resources\\static\\userBg\\");
        registry.addResourceHandler("/userBg/**").addResourceLocations("file:C:\\Program Files\\apache-tomcat-9.0.13\\apache-tomcat-9.0.13\\webapps\\springboot\\WEB-INF\\classes\\static\\userBg\\");
//addResourceHandler()是将相对路径填进来，"/userImage/**"表示static下面的userImag里面的全部内容，都映射到绝对路径为"C:\\Users\\Administrator\\Documents\\GitHub\\forum\\forum\\src\\main\\resources\\static\\userBg\\"的文件夹里面去查找，这样就可以立即找到了。
//我这里之所以用来两次是因为我上传头像和背景图的文件夹不是同一个，所以分别映射了两次。注意:file必须要加！！
    }
}

