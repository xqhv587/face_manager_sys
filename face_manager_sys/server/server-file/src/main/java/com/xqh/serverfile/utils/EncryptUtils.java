package com.xqh.serverfile.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EncryptUtils {
    private static final Logger logger = LoggerFactory.getLogger(EncryptUtils.class);

    /**
     * 加密
     *
     * @param content
     * @return
     */
    public static String encode(String content) {

        try {
            String aesKey = AES256Utils.getStringRandom(16);
            logger.info("EncryptUtils", "encode aesKey=" + aesKey);
            String aesResult = AES256Utils.AES_Encode(content, aesKey);
            String result = aesResult + ":::" +
                    RsaUtils.encrypt(Base64Utils.encode(aesKey.getBytes()), RsaUtils.PUB_KEY, "UTF-8");
            logger.info("EncryptUtils", "encode result=" + result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 解密
     *
     * @param encryptedContent
     * @return
     */
    public static String decode(String encryptedContent) {

        try {
            String[] content = encryptedContent.split(":::");
            if (content != null && content.length == 2) {
                String aesKey = RsaUtils.decrypt(content[1], RsaUtils.PRI_KEY, "UTF-8");
                logger.info("EncryptUtils", "decode aesKey=" + aesKey);
                String result = AES256Utils.AES_Decode(content[0], new String(Base64Utils.decode(aesKey), "UTF-8"));
                logger.info("EncryptUtils", "decode result=" + result);
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }


}