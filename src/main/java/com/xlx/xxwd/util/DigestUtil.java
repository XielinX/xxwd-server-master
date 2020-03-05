package com.xlx.xxwd.util;

import com.xlx.xxwd.exception.ErrorCodeEnum;
import com.xlx.xxwd.exception.ErrorCodeException;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * sha1加密
 *
 * @author xielx on 2019/7/2
 */
public class DigestUtil {
    
    private static Logger logger = LoggerFactory.getLogger(DigestUtil.class);
    
    /**
     * signature = sha1(rawData + sessionKey)
     *
     * @param rawData
     * @param sessionKey
     * @param signature
     */
    public static void checkDigest(String rawData, String sessionKey, String signature) {
        
        //logger.info("rawData:{},sessionKey:{},signature:{}", rawData, sessionKey, signature);
        String sha1_signature = DigestUtils.sha1Hex((rawData + sessionKey).getBytes());
        
        logger.info("sha1_signature[{}]:", sha1_signature);
        if (!sha1_signature.equals(signature)) {
            logger.error("检验失败:signature != SHA1(rawData + session_key)");
            throw new ErrorCodeException(ErrorCodeEnum.SIGNATURE_ERROR);
        }
        
    }
}
