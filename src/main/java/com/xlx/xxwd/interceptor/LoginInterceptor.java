package com.xlx.xxwd.interceptor;

import com.alibaba.fastjson.JSON;
import com.xlx.xxwd.dto.ResultDTO;
import com.xlx.xxwd.exception.ErrorCodeEnum;
import com.xlx.xxwd.model.User;
import com.xlx.xxwd.service.UserService;
import com.xlx.xxwd.session.SessionUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 登录拦截器
 * Alt+Shift+p
 *
 * @author xielx on 2019/7/2
 */
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

  @Resource
  private  UserService userService;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    String token = request.getHeader("token");
    if (StringUtils.isBlank(token)){
      validateToken(response);
      return false;
    }

    //通过token,从数据库中取出User
    User user = userService.getUserByToken(token);
    if (user == null){
      validateToken(response);
      return false;
    }

    //把User暂存到ThreadLocal里,以便上下文中方便使用
    SessionUtil.setUser(user);
    return true;

  }


  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    //此方法是Controller方法执行后执行

    //请求结束以后,移除User
    SessionUtil.removeUser();


  }

  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    //视图渲染完成返回客户端前执行
  }

  private void validateToken(HttpServletResponse response){
    ResultDTO resultDTO = ResultDTO.failed(ErrorCodeEnum.USER_NOT_FOUND);
    response.setCharacterEncoding("UTF-8");
    response.setContentType("application/json;charset=utf-8");
    try {
     PrintWriter out = response.getWriter();
     out.print(JSON.toJSONString(resultDTO));
     out.flush();
     out.close();
    } catch (IOException e) {
      log.error("LoginController preHandle error,[{}]",e);
    }

  }
}
