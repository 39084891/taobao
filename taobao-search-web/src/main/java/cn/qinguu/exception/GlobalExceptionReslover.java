package cn.qinguu.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: GlobalExceptionReslover
 * @Description 全局异常处理类
 * @Author cy
 * @Date 2019/4/1214:23
 * @Version 1.0
 **/
public class GlobalExceptionReslover implements HandlerExceptionResolver {

    Logger logger = LoggerFactory.getLogger(GlobalExceptionReslover.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        logger.error("系统出现异常!", e);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message", "系统出现异常,请稍后再试");
        modelAndView.setViewName("error/exception");
        return modelAndView;
    }
}
