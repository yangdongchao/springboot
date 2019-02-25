package com.example.demo.web;

import com.example.demo.cons.Constantion;
import com.example.demo.domain.User;
import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @ClassName BaseController
 * @Description TODO
 * @Auther ydc
 * @Date 2019/1/10 21:28
 * @Version 1.0
 **/
public class BaseController  {
    protected static final String ERROR_MSG_KEY = "errorMsg";

    /**
     * 将用户存入session
     * @param request
     * @return
     */
    protected void setSessionUser(HttpServletRequest request,User user) {
        request.getSession().setAttribute(Constantion.USER_CONTEXT,
                user);
    }

    /**
     * 获得user
     * @param request
     * @return
     */
    protected User getSessionUser(HttpServletRequest request) {
        return (User) request.getSession().getAttribute(
                Constantion.USER_CONTEXT);
    }

    /**
     * 获取基于应用程序的url绝对路径
     *
     * @param request
     * @param url
     *            以"/"打头的URL地址
     * @return 基于应用程序的url绝对路径
     */
    public final String getAppbaseUrl(HttpServletRequest request, String url) {
        Assert.hasLength(url, "url不能为空");
        Assert.isTrue(url.startsWith("/"), "必须以/打头");
        return request.getContextPath() + url;
    }

    protected HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    protected HttpSession getSession() {
        return getRequest().getSession();
    }
}
