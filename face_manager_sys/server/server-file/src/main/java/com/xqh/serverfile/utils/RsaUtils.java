package com.xqh.serverfile.utils;

import javax.crypto.Cipher;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * RSA密钥生成、加解密
 */
public class RsaUtils {

    /**
     * RsaUtils:加密算法
     */
    public static final String KEY_ALGORITHM = "RSA";

    public static final String KEY_ALGORITHM_RSA_NONE = "RSA/None/PKCS1Padding";

    /**
     * SHA1WithRSA:用SHA算法进行签名，用RSA算法进行加密
     */
    public static final String SIGN_ALGORITHMS = "SHAWithRSA";


    /**
     * 公钥
     */
    public static final String PUB_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCfiSWsHFz0UZSv1JrP6bbUNWsi" +
            "PzPBDSJ0PfAq54b1jRTxlPOXoBkg7hEKeH/Qhmi+bDowfy4cydTLGsa2bHDQj3NZ" +
            "GqRBfeYxm2OdiUYIrpAiaQmGHz2MNBxIg1hzzRG62IqwYLJQG3/raWM8LwVhYm79" +
            "M3/CxQeTOw9ENeXthQIDAQAB";

    /**
     * 私钥
     */
    public static final String PRI_KEY = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJ+JJawcXPRRlK/U" +
            "ms/pttQ1ayI/M8ENInQ98CrnhvWNFPGU85egGSDuEQp4f9CGaL5sOjB/LhzJ1Msa" +
            "xrZscNCPc1kapEF95jGbY52JRgiukCJpCYYfPYw0HEiDWHPNEbrYirBgslAbf+tp" +
            "YzwvBWFibv0zf8LFB5M7D0Q15e2FAgMBAAECgYBOy51IuaWLonOOuLJR+Hwtqcil" +
            "XJQCSKt4wFLLzhjRVQJAI7KAdDmVEyBljIiMhKZdTvUamOY8n88zFaGJ3luFT3zb" +
            "qe/PT/GwIRqHR0eow0KrjFuc8Tr++5odme43Mn/ImRJKVm7U+HKldSygDzGMEl5P" +
            "V4XQ0zqDizgfL7GRwQJBAM1NgIHKFsQYesaUbdyDQw8b914rW6O+tEkpHalgerGk" +
            "MeSqka8udoRIuWAGXN7eRWjQjSxlCtXNvKgBu1EEjLECQQDG7muFQB4GtXEFngOh" +
            "2ubA44aMDIZkTq9fXd0JT+r9/9bQ4UFqTYd0oCNZ8eVhbb+j9qulR3a32Iezug3b" +
            "uVMVAkEAvEIVVO67cZYA/5ecJlk3ZOOAMhoQLlbxeyT4zhhqafrmxYijjsBMbYFC" +
            "e4taHgQ0Iakby1ncVbITDPyJiAFkAQJAGtqumxNXulIeCsMU/HF/ewR78kXyknHo" +
            "zLfwEcS/FUDHL/VojkAfDAheHVNBQtC88RZof2xRQ2owj3uLSWEQLQJAV6IqYSDB" +
            "NmnLrLwc2cZHo2ZdS5GfZKk+csVNi4BB7QspsYr+jE81Ed3tiMXI9jY0tZIysQmY" +
            "AhNHl7P8Zxwl+Q==";
    /**
     * 块加密的大小，是不要太大，否则效率会低
     */
    private static final int KEY_SIZE = 1024;

    /**
     * 获取公钥的key
     */
    private static final String PUBLIC_KEY = "RSAPublicKey";

    /**
     * 获取私钥的key
     */
    private static final String PRIVATE_KEY = "RSAPrivateKey";

    /**
     * RSA最大加密明文大小
     */
    private static final int MAX_ENCRYPT_BLOCK = 117;

    /**
     * RSA最大解密密文大小
     */
    private static final int MAX_DECRYPT_BLOCK = 128;


    /**
     * * 生成密钥对 *
     *
     * @return KeyPair *
     * @throws Exception
     */
    public static KeyPair generateKeyPair() throws Exception {
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        keyPairGen.initialize(KEY_SIZE, new SecureRandom());
        KeyPair keyPair = keyPairGen.generateKeyPair();
        return keyPair;
    }

    /**
     * * 生成公钥 *
     *
     * @param keyPair *
     * @return RSAPublicKey *
     * @throws Exception
     */
    public static String generateRSAPublicKey(KeyPair keyPair) throws Exception {

        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        return Base64Utils.encode(publicKey.getEncoded());
    }

    /**
     * * 生成私钥 *
     *
     * @param keyPair *
     * @return RSAPublicKey *
     * @throws Exception
     */
    public static String generateRSAPrivateKey(KeyPair keyPair) throws Exception {

        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        return Base64Utils.encode(privateKey.getEncoded());
    }

    /**
     * * 生成公私钥对 *
     *
     * @return Map *
     * @throws Exception
     */
    public static Map<String, Object> generateRSAKey() throws Exception {
        KeyPair keyPair = generateKeyPair();
        Map<String, Object> keyMap = new HashMap<String, Object>(2);
        keyMap.put(PUBLIC_KEY, generateRSAPublicKey(keyPair));
        keyMap.put(PRIVATE_KEY, generateRSAPrivateKey(keyPair));
        return keyMap;
    }

    /**
     * 用私钥对信息生成数字签名
     *
     * @param content       待签名数据
     * @param privateKey    私钥(BASE64编码)
     * @param input_charset 编码格式
     * @return 签名值
     */
    public static String sign(String content, String privateKey, String input_charset) throws Exception {
        PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(Base64Utils.decode(privateKey));
        KeyFactory keyf = KeyFactory.getInstance(KEY_ALGORITHM);
        PrivateKey priKey = keyf.generatePrivate(priPKCS8);

        Signature signature = Signature.getInstance(SIGN_ALGORITHMS);
        signature.initSign(priKey);
        signature.update(content.getBytes(input_charset));
        byte[] signed = signature.sign();
        return Base64Utils.encode(signed);
    }

    /**
     * 用公钥对信息RSA验签名检查
     *
     * @param content        待签名数据
     * @param sign           签名值
     * @param yzf_public_key 公钥
     * @param input_charset  编码格式
     * @return 布尔值
     */
    public static boolean verify(String content, String sign, String yzf_public_key, String input_charset) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        byte[] encodedKey = Base64Utils.decode(yzf_public_key);
        PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));

        Signature signature = Signature.getInstance(SIGN_ALGORITHMS);

        signature.initVerify(pubKey);
        signature.update(content.getBytes(input_charset));

        return signature.verify(Base64Utils.decode(sign));
    }

    /**
     * 使用公钥RSA加密
     *
     * @param content        明文
     * @param yzf_public_key 公钥
     * @param input_charset  编码格式
     * @return 解密后的字符串
     */
    public static String encrypt(String content, String yzf_public_key, String input_charset) throws Exception {

        String str = null;
        ByteArrayOutputStream writer = null;
        try {
            PublicKey pubKey = getPublicKey(yzf_public_key);

            Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, pubKey);

            // 设置编码格式
            InputStream ins = new ByteArrayInputStream(content.getBytes(input_charset));
            writer = new ByteArrayOutputStream();

            byte[] buf = new byte[MAX_DECRYPT_BLOCK];
            int bufl;

            while ((bufl = ins.read(buf)) != -1) {
                byte[] block = null;

                if (buf.length == bufl) {
                    block = buf;
                } else {
                    block = new byte[bufl];
                    for (int i = 0; i < bufl; i++) {
                        block[i] = buf[i];
                    }
                }
                writer.write(cipher.doFinal(block));
            }
            str = new String(Base64Utils.encode(writer.toByteArray()));
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                writer = null;
            }
        }
        return str;
    }

    /**
     * 使用私钥RSA解密
     *
     * @param content       密文
     * @param private_key   商户私钥
     * @param input_charset 编码格式
     * @return 解密后的字符串
     */
    public static String decrypt(String content, String private_key, String input_charset) throws Exception {
        PrivateKey prikey = getPrivateKey(private_key);

        Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, prikey);

        InputStream ins = new ByteArrayInputStream(Base64Utils.decode(content));
        ByteArrayOutputStream writer = new ByteArrayOutputStream();
        //rsa解密的字节大小最多是128，将需要解密的内容，按128位拆开解密
        byte[] buf = new byte[MAX_DECRYPT_BLOCK];
        int bufl;

        while ((bufl = ins.read(buf)) != -1) {
            byte[] block = null;

            if (buf.length == bufl) {
                block = buf;
            } else {
                block = new byte[bufl];
                for (int i = 0; i < bufl; i++) {
                    block[i] = buf[i];
                }
            }
            writer.write(cipher.doFinal(block));
        }

        return new String(writer.toByteArray(), input_charset);
    }

    /**
     * 得到私钥
     *
     * @param key 密钥字符串（经过base64编码）
     * @throws Exception
     */
    public static PrivateKey getPrivateKey(String key) throws Exception {

        byte[] keyBytes = Base64Utils.decode(key);

        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);

        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);

        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);

        return privateKey;
    }

    /**
     * 得到公钥
     *
     * @param key 加密字符串（经过base64编码）
     * @throws Exception
     */
    public static PublicKey getPublicKey(String key) throws Exception {

        byte[] keyBytes;

        keyBytes = Base64Utils.decode(key);

        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);

        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);

        PublicKey publicKey = keyFactory.generatePublic(keySpec);

        return publicKey;
    }

    public static void main(String args[]) {

        try {

            String content = "我叫howsun";
            String encryStr = RsaUtils.encrypt(content, RsaUtils.PUB_KEY, "UTF-8");
            System.out.println("加密结果：" + encryStr);
            String decryStr = RsaUtils.decrypt(encryStr, RsaUtils.PRI_KEY, "UTF-8");
            System.out.println("解密结果：" + decryStr);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}