package com.panda.esportingplus.common.tools;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;


/**
 * RSA加解密工具类--采用1024位
 * 1024位的证书,加密时最大支持117个字节,解密时为128
 * 2048位的证书,加密时最大支持245个字节,解密时为256
 * 所有字符串未特殊说明皆为Base64编码的格式
 * 由于可以通过私钥计算出公钥,请使用公钥加密,私钥解密;私钥加密公钥解密用于数字签名场合
 * 参考: https://www.cnblogs.com/nanyangke-cjz/p/5898361.html
 * @author shusong.liang
 * @date 2020/03/25 11:12:56
 */
public final class RSAUtils {

    /**
     * 指定算法为RSA
     */
    private static final String ALGORITHM = "RSA";

    /**
     * 指定key的大小
     */
    private static final int KEY_SIZE = 1024;

    /**
     * RSA 公钥
     */
    private static final String PUBLIC_KEY = "publicKey";

    /**
     * RSA 私钥
     */
    private static final String PRIVATE_KEY = "privateKey";

    /**
     * 指定分段时最大加密的字节长度
     * 这个参数可以自定义,小于117(2048位为245)即可
     */
    private static final int MAX_ENCRYPT_BLOCK_SIZE = KEY_SIZE / 8 - 11;

    /**
     * 指定分段时最大解密的字节长度
     * 这个参数自定义会抛出 javax.crypto.BadPaddingException: Decryption error
     * 因此使用其默认值
     */
    private static final int MAX_DECRYPT_BLOCK_SIZE = KEY_SIZE / 8;

    /**
     * 指定签名算法为 MD5
     */
    private static final String SIGNATURE_ALGORITHM = "MD5withRSA";

    /**
     * 生成一对公私钥对
     *
     * @return
     */
    public static Map<String, Key> generateKey() {
        SecureRandom sr = new SecureRandom();
        KeyPairGenerator kpg = null;
        try {
            kpg = KeyPairGenerator.getInstance(ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        kpg.initialize(KEY_SIZE, sr);
        KeyPair kp = kpg.generateKeyPair();
        RSAPublicKey publicKey = (RSAPublicKey) kp.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) kp.getPrivate();

        Map<String, Key> map = new HashMap<>();
        map.put(PUBLIC_KEY, publicKey);
        map.put(PRIVATE_KEY, privateKey);
        return map;
    }

    /**
     * 返回 RSA 公钥的 Base64 字符串
     *
     * @param publicKey
     * @return Base64 编码的 RSA 公钥字符串
     */
    public static String getPublicKeyStr(RSAPublicKey publicKey) {
        return Base64.getEncoder().encodeToString(publicKey.getEncoded());
    }

    /**
     * 返回 RSA 私钥的 Base64 字符串
     *
     * @param privateKey
     * @return Base64 编码的 RSA 私钥字符串
     */
    public static String getPrivateKeyStr(RSAPrivateKey privateKey) {
        return Base64.getEncoder().encodeToString(privateKey.getEncoded());
    }

    /**
     * 根据公钥 Base64 字符串生成公钥
     *
     * @param publicKeyStr RSA 公钥的 Base64 字符串
     * @return RSAPublicKey
     */
    public static RSAPublicKey generatorPublicKeyByStr(String publicKeyStr) throws InvalidKeySpecException {
        byte[] pbkBytes = Base64.getDecoder().decode(publicKeyStr);
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(pbkBytes);
        try {
            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
            return (RSAPublicKey) keyFactory.generatePublic(x509EncodedKeySpec);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据私钥 Base64 字符串生成私钥
     *
     * @param privateStr RSA 私钥的 Base64 字符串
     * @return RSAPrivateKey
     */
    public static RSAPrivateKey generatorPrivateKeyByStr(String privateStr) throws InvalidKeySpecException {
        byte[] prkBytes = Base64.getDecoder().decode(privateStr);
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(prkBytes);
        try {
            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
            return (RSAPrivateKey) keyFactory.generatePrivate(pkcs8EncodedKeySpec);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 使用 RSA 公钥加密
     *
     * @param originalText 原文
     * @param publicKey    公钥
     * @return Base64 编码后的密文
     * @throws Exception
     */
    public static String encryptByPublicKey(String originalText, RSAPublicKey publicKey) throws Exception {
        return encryptData(originalText, publicKey);
    }


    /**
     * 使用 RSA 私钥解密
     *
     * @param encryptText Base64 密文
     * @param privateKey  私钥
     * @return 私钥解密后的字符串(未使用Base64编码)
     * @throws Exception
     */
    public static String decryptByPrivateKey(String encryptText, RSAPrivateKey privateKey) throws Exception {
        return decryptData(encryptText, privateKey);
    }

    /**
     * 分段处理数据
     *
     * @param data      要处理的数据
     * @param cipher    Cipher 对象
     * @param isEncrypt true: 加密|false: 解密
     * @return 分段处理后数据
     * @throws IOException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    private static byte[] blockDoFinal(byte[] data, Cipher cipher, boolean isEncrypt) throws IOException, IllegalBlockSizeException, BadPaddingException {
        int blockSize;
        if (isEncrypt) {
            blockSize = MAX_ENCRYPT_BLOCK_SIZE;
        } else {
            blockSize = MAX_DECRYPT_BLOCK_SIZE;
        }
        int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段处理
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > blockSize) {
                cache = cipher.doFinal(data, offSet, blockSize);
            } else {
                cache = cipher.doFinal(data, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * blockSize;
        }
        byte[] doFinalData = out.toByteArray();
        out.close();
        return doFinalData;
    }

    /**
     * 使用 RSA 私钥加密
     *
     * @param originalText 原文
     * @param privateKey   私钥
     * @return Base64 编码后的密文
     * @throws Exception
     */
    public static String encryptByPrivateKey(String originalText, RSAPrivateKey privateKey) throws Exception {
        return encryptData(originalText, privateKey);
    }

    /**
     * 使用 RSA 公钥解密
     *
     * @param encryptText Base64 密文
     * @param publicKey  公钥
     * @return 公钥解密后的字符串(未使用Base64编码)
     * @throws Exception
     */
    public static String decryptByPublicKey(String encryptText, RSAPublicKey publicKey) throws Exception {
        return decryptData(encryptText, publicKey);
    }

    /**
     * 加密数据
     *
     * @param originalText 原文
     * @param key RSA 公钥/私钥
     * @return
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws IOException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    private static String encryptData(String originalText, Key key) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IOException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] doFinalData = blockDoFinal(originalText.getBytes(), cipher, true);
        return Base64.getEncoder().encodeToString(doFinalData);
    }

    /**
     * 解密数据
     *
     * @param encryptText Base64 编码后的密文
     * @param key RSA 公钥/私钥
     * @return
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws IOException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    private static String decryptData(String encryptText, Key key) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IOException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decodeBytes = Base64.getDecoder().decode(encryptText);
        byte[] doFinalData = blockDoFinal(decodeBytes, cipher, false);
        return new String(doFinalData);
    }

    /**
     * 利用模和幂生成 RSA 公钥
     *
     * @param modulus        模
     * @param publicExponent 幂
     * @return RSAPublicKey
     */
    public static RSAPublicKey generatePublicKey(String modulus, String publicExponent) {
        try {
            byte[] m = Base64.getDecoder().decode(modulus);
            byte[] e = Base64.getDecoder().decode(publicExponent);

            BigInteger mb = new BigInteger(1, m);
            BigInteger eb = new BigInteger(1, e);
            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
            RSAPublicKeySpec keySpec = new RSAPublicKeySpec(mb, eb);
            return (RSAPublicKey) keyFactory.generatePublic(keySpec);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 利用模和幂生成 RSA 私钥
     *
     * @param modulus         模
     * @param privateExponent 幂
     * @return RSAPrivateKey
     */
    public static RSAPrivateKey generatePrivateKey(String modulus, String privateExponent) {
        try {
            byte[] m = Base64.getDecoder().decode(modulus);
            byte[] e = Base64.getDecoder().decode(privateExponent);
            BigInteger mb = new BigInteger(1, m);
            BigInteger eb = new BigInteger(1, e);
            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
            RSAPrivateKeySpec keySpec = new RSAPrivateKeySpec(mb, eb);
            return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 使用 RSA 私钥生成数字签名
     *
     * @param data       需要签名的数据
     * @param privateKey RSAPrivateKey
     * @return 私钥生成的数字签名
     * @throws Exception
     */
    public static String generateSignature(byte[] data, RSAPrivateKey privateKey) throws Exception {
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initSign(privateKey);
        signature.update(data);
        return Base64.getEncoder().encodeToString(signature.sign());
    }

    /**
     * 使用 RSA 公钥校验数字签名
     *
     * @param data      需要校验的数据
     * @param publicKey RSAPublicKey
     * @param signature 使用 RSA 私钥生成的数字签名
     * @return 公钥校验的签名结果
     * @throws Exception
     */
    public static boolean verifySignature(byte[] data, RSAPublicKey publicKey, String signature) throws Exception {
        Signature sign = Signature.getInstance(SIGNATURE_ALGORITHM);
        sign.initVerify(publicKey);
        sign.update(data);
        return sign.verify(Base64.getDecoder().decode(signature));
    }

    // 测试
    public static void main(String[] args) {
        try {
            Map<String, Key> map = generateKey();
            String originText = "0123456789"
                    + "一首凉凉送给自己"
                    + "abcdefghijklmnopqrstuvwxyz"
                    + "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                    + "@`!#$%^&*()_+|}{}{|><?//\\|~"
                    + "、。·ˉˇ¨I〃々—～...";
            RSAPublicKey publicKey = (RSAPublicKey) map.get(PUBLIC_KEY);
            System.out.println("map里的公钥:" + publicKey);
            RSAPrivateKey privateKey = (RSAPrivateKey) map.get(PRIVATE_KEY);
            System.out.println("map里的私钥:" + privateKey);
            String publicKeyStr = getPublicKeyStr(publicKey);
            System.out.println("公钥字符串: "+ publicKeyStr);
            String privateKeyStr = getPrivateKeyStr(privateKey);
            System.out.println("私钥字符串: "+ privateKeyStr);
            publicKey = generatorPublicKeyByStr(publicKeyStr);
            System.out.println("根据公钥字符串生成的公钥: "+publicKey);
            privateKey = generatorPrivateKeyByStr(privateKeyStr);
            System.out.println("根据私钥字符串生成的私钥: "+privateKey);
            System.out.println("==================================================================");
            String encryptPublicString = encryptByPublicKey(originText, publicKey);
            System.out.println("公钥加密后的密文:" + encryptPublicString);
            String decryptPrivateString = decryptByPrivateKey(encryptPublicString, privateKey);
            System.out.println("私钥解密后的内容:" + decryptPrivateString);
            String encryptPrivateString = encryptByPrivateKey(originText, privateKey);
            System.out.println("私钥加密后的密文:" + encryptPrivateString);
            String decryptPublicString = decryptByPublicKey(encryptPrivateString, publicKey);
            System.out.println("公钥解密后的内容:" + decryptPublicString);
            System.out.println("原文:" + originText);
            String signature = generateSignature(originText.getBytes(), privateKey);
            System.out.println("私钥生成的数字签名:" + signature);
            boolean verify = verifySignature(originText.getBytes(), publicKey, signature);
            System.out.println("公钥校验数字签名:" + verify);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

