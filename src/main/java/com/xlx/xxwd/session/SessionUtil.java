package com.xlx.xxwd.session;

import com.xlx.xxwd.entity.User;

/**
 * session工具类
 *
 * @author xielx on 2019/7/2
 */
public class SessionUtil {

  // 线程局部变量
  private static ThreadLocal<User> userThreadLocal = new ThreadLocal<>();

  //设置局部变量的当前线程副本中的值
  public static User getUser(){
    return userThreadLocal.get();
  }

  public static void setUser(User user){
    userThreadLocal.set(user);
  }

  public static void removeUser(){
    userThreadLocal.remove();
  }

}
