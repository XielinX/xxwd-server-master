package com.xlx.xxwd;

/**
 * @author xielx on 2019/7/2
 */
public class Test {


  public static void main(String[] args) {

    String url = "https://api.weixin.qq.com/sns/jscode2session?appid=&secret=%s&js_code=&grant_type=authorization_code";
    String appid ="asa1ssa1s22345";
    String secrect = "xlx.20123";
    String code = "adddf15sd12a5a1a4d6";
    System.out.println("格式:" + String.format(url,appid,secrect,code));
  }
}
