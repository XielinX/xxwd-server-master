package com.xlx.xxwd.util;

import com.xlx.xxwd.exception.ErrorCodeEnum;
import com.xlx.xxwd.exception.ErrorCodeException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * sha1加密
 *
 * @author xielx on 2019/7/2
 */
@Slf4j
public class DigestUtil {


  /**
   * signature = sha1(rawData + sessionKey)
   *
   * @param rawData
   * @param sessionKey
   * @param signature
   */
  public static void checkDigest(String rawData, String sessionKey, String signature) {
  
    log.info("rawData:{},sessionKey:{},signature:{}", rawData, sessionKey, signature);
    String sha1 = DigestUtils.sha1Hex((rawData + sessionKey).getBytes());

    log.info("sha1[{}]:",sha1);
    if (!sha1.equals(signature)) {
      throw new ErrorCodeException(ErrorCodeEnum.SIGNATURE_ERROR);
    }

  }
}
