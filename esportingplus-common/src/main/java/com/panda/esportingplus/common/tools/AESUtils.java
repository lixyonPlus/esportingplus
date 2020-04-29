package com.panda.esportingplus.common.tools;

import java.io.ByteArrayOutputStream;
import java.nio.charset.Charset;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.util.Base64;
import java.util.Base64.Decoder;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Base64Utils;


/**
 * AES128 算法 CBC 模式 PKCS7Padding 填充模式
 *
 * 其中CBC模式需要添加一个参数iv--对称解密算法初始向量 iv 要实现用PKCS7Padding填充，需要用到bouncycastle组件来实现
 * 
 * @author haycco
 */
public class AESUtils {

    private static final Logger logger = LoggerFactory.getLogger(AESUtils.class);

    // 算法名称
    public static final String KEY_ALGORITHM = "AES";
    public static final String DEFAULT_CHARSET = "UTF8";
    // 加解密算法/模式/填充方式
    public static final String ALGORITHM = "AES/CBC/PKCS7Padding";

    /**
     * 解密数据
     * @param content 数据Base64编码
     * @param key 秘钥Base64编码
     * @param iv 向量Base64编码
     * @return 数据原文
     */
    public static String decrypt(String content, String key, String iv) {
        return decrypt(content, key, iv, DEFAULT_CHARSET);
    }

    /**
     * 解密数据
     * @param content 数据Base64编码
     * @param key 秘钥Base64编码
     * @param iv 向量Base64编码
     * @param charset 字符编码
     * @return 数据原文
     */
    public static String decrypt(String content, String key, String iv, String charset) {
        Decoder d = Base64.getDecoder();
        byte[] bytes = decryptOfDiyIV(d.decode(content), d.decode(key), d.decode(iv));
        if (bytes != null && bytes.length != 0) {
            return new String(bytes, Charset.forName(charset));
        }
        return null;
    }

    /**
     * 加密方法 ---自定义对称解密算法初始向量 iv
     * 
     * @param content 要加密的字符串
     * @param keyBytes 加密密钥
     * @param ivs 自定义对称解密算法初始向量 iv
     * @return 加密的结果
     */
    public static byte[] encryptOfDiyIV(byte[] content, byte[] keyBytes, byte[] ivs) {
        byte[] encryptedText = null;
        logger.debug("加密向量IV：" + new String(Base64Utils.encode(ivs)));
        try {
            // 如果密钥不足16位，那么就补足
            int base = 16;
            if (keyBytes.length % base != 0) {
                int groups = keyBytes.length / base + (keyBytes.length % base != 0 ? 1 : 0);
                byte[] temp = new byte[groups * base];
                Arrays.fill(temp, (byte) 0);
                System.arraycopy(keyBytes, 0, temp, 0, keyBytes.length);
                keyBytes = temp;
            }
            // 初始化
            Security.addProvider(new BouncyCastleProvider());
            // 转化成JDK加密的密钥格式
            Key key = new SecretKeySpec(keyBytes, KEY_ALGORITHM);
            // 初始化cipher
            Cipher cipher = Cipher.getInstance(ALGORITHM, "BC");
            cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(ivs));
            
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            bos.write(cipher.doFinal(content));
            encryptedText = bos.toByteArray();
        } catch (NoSuchAlgorithmException e) {
            logger.error(e.getMessage());
        } catch (NoSuchPaddingException e) {
            logger.error(e.getMessage());
        } catch (NoSuchProviderException e) {
            logger.error(e.getMessage());
        } catch (Exception e) {
            logger.error("加密异常：" + e.getMessage());
        }
        return encryptedText;
    }

    /**
     * 解密方法
     *
     * @param encryptedData 要解密的字符串
     * @param keyBytes 解密密钥
     * @param ivs 自定义对称解密算法初始向量 iv
     * @return
     */
    public static byte[] decryptOfDiyIV(byte[] encryptedData, byte[] keyBytes, byte[] ivs) {
        byte[] encryptedText = null;
        logger.debug("解密向量IV：" + new String(Base64Utils.encode(ivs)));
        try {
            // 如果密钥不足16位，那么就补足
            int base = 16;
            if (keyBytes.length % base != 0) {
                int groups = keyBytes.length / base + (keyBytes.length % base != 0 ? 1 : 0);
                byte[] temp = new byte[groups * base];
                Arrays.fill(temp, (byte) 0);
                System.arraycopy(keyBytes, 0, temp, 0, keyBytes.length);
                keyBytes = temp;
            }
            // 初始化
            Security.addProvider(new BouncyCastleProvider());
            // 转化成JDK加密的密钥格式
            Key key = new SecretKeySpec(keyBytes, KEY_ALGORITHM);
            // 初始化cipher
            Cipher cipher = Cipher.getInstance(ALGORITHM, "BC");
            cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(ivs));
          
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            bos.write(cipher.doFinal(encryptedData));
            encryptedText = bos.toByteArray();
        } catch (NoSuchAlgorithmException e) {
            logger.error(e.getMessage());
        } catch (NoSuchPaddingException e) {
            logger.error(e.getMessage());
        } catch (NoSuchProviderException e) {
            logger.error(e.getMessage());
        } catch (Exception e) {
            logger.error("解密异常：" + e.getMessage());
        }
        return encryptedText;
    }

    public static void main(String[] args) {

        // 密文
        String encryptedData = "CiyLU1Aw2KjvrjMdj8YKliAjtP4gsMZMQmRzooG2xrDcvSnxIMXFufNstNGTyaGS9uT5geRa0W4oTOb1WT7fJlAC+oNPdbB+3hVbJSRgv+4lGOETKUQz6OYStslQ142dNCuabNPGBzlooOmB231qMM85d2/fV6ChevvXvQP8Hkue1poOFtnEtpyxVLW1zAo6/1Xx1COxFvrc2d7UL/lmHInNlxuacJXwu0fjpXfz/YqYzBIBzD6WUfTIF9GRHpOn/Hz7saL8xz+W//FRAUid1OksQaQx4CMs8LOddcQhULW4ucetDf96JcR3g0gfRK4PC7E/r7Z6xNrXd2UIeorGj5Ef7b1pJAYB6Y5anaHqZ9J6nKEBvB4DnNLIVWSgARns/8wR2SiRS7MNACwTyrGvt9ts8p12PKFdlqYTopNHR1Vf7XjfhQlVsAJdNiKdYmYVoKlaRv85IfVunYzO0IKXsyl7JCUjCpoG20f0a04COwfneQAGGwd5oa+T8yO5hzuyDb/XcxxmK01EpqOyuxINew==";
        //向量
        String iv = "r7BXXKkLb8qrSNn05n0qiA==";
        //秘钥
        String sessionKey = "tiihtNczf5v6AKRyjwEUhQ==";
        // 解密
        String dec = AESUtils.decrypt(encryptedData, sessionKey, iv);
        System.out.println(dec);
    }

}
