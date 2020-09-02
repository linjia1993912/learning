package com.linjia.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Description:
 * @Author LinJia
 * @Date 2020/9/1
 **/
@Controller
public class MyController {

    /**
     * @Description:返回值hello，根据前面的配置文件会去/WEB-INF/page/下面找hello.jsp。所以需要新建这个jsp
     * @Author LinJia
     * @Date 2020/9/1 16:27
     * @Param []
     * @return java.lang.String
     **/
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public ModelAndView hello() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message","Hello SpringMVC");
        return modelAndView;
    }
}
