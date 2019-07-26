package com.demo.util;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;


public class AesEncryptionUtil {

    private static final String CIPHER_MODE = AesEnum.valueOf("FIRST").getCipherMode();
    private static final String KEY = AesEnum.valueOf("FIRST").getKey();
    private static final String IV = AesEnum.valueOf("FIRST").getIv();
    private static final String DATE = AesEnum.valueOf("FIRST").getDate();
    private static final String ENCODEING = "UTF-8";

    private AesEncryptionUtil() {
    }

    private static SecretKeySpec createKey(String key) {
        byte[] data = null;
        if (key == null) {
            key = "";
        }

        StringBuilder sb = new StringBuilder(16);
        sb.append(key);

        while(sb.length() < 16) {
            sb.append("0");
        }

        if (sb.length() > 16) {
            sb.setLength(16);
        }

        try {
            data = sb.toString().getBytes("UTF-8");
        } catch (UnsupportedEncodingException var4) {
           // LogUtils.logError(var4);
        }

        return new SecretKeySpec(data, "AES");
    }

    private static IvParameterSpec createIV(String password) {
        byte[] data = null;
        if (password == null) {
            password = "";
        }

        StringBuilder sb = new StringBuilder(16);
        sb.append(password);

        while(sb.length() < 16) {
            sb.append("0");
        }

        if (sb.length() > 16) {
            sb.setLength(16);
        }

        try {
            data = sb.toString().getBytes("UTF-8");
        } catch (UnsupportedEncodingException var4) {
            //LogUtils.logError(var4.getMessage());
        }

        return new IvParameterSpec(data);
    }

    private static byte[] encrypt(byte[] content) {
        try {
            SecretKeySpec key = createKey(KEY);
            Cipher cipher = Cipher.getInstance(CIPHER_MODE);
            cipher.init(1, key, createIV(IV));
            return cipher.doFinal(content);
        } catch (Exception var3) {
           // LogUtils.logError(var3);
            return new byte[0];
        }
    }

    public static String encrypt(String content) {
        String data;
        try {
            if (content.startsWith("xy")) {
                return null;
            }

            data = byteToHexString(encrypt(content.getBytes("UTF-8")));
        } catch (Exception var3) {
           // LogUtils.logError(var3);
            return null;
        }

        return "xy" + data + DATE;
    }

    private static byte[] decrypt(byte[] content) {
        try {
            SecretKeySpec key = createKey(KEY);
            Cipher cipher = Cipher.getInstance(CIPHER_MODE);
            cipher.init(2, key, createIV(IV));
            return cipher.doFinal(content);
        } catch (Exception var3) {
            //LogUtils.logInfo(var3);
            return new byte[0];
        }
    }

    public static String decrypt(String content) {
        String result = null;

        try {
            if (!content.startsWith("xy")) {
                return content;
            }

            content = content.substring(2);
            content = content.substring(0, content.length() - 8);
            byte[] data = decrypt(hexStringToByte(content));
            if (data == null) {
                return result;
            }

            result = new String(data, "UTF-8");
        } catch (Exception var3) {
           // LogUtils.logError(var3);
        }

        return result;
    }

    private static String byteToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        byte[] var2 = bytes;
        int var3 = bytes.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            byte b = var2[var4];
            String strHex = Integer.toHexString(b);
            if (strHex.length() > 3) {
                sb.append(strHex.substring(6));
            } else if (strHex.length() < 2) {
                sb.append("0").append(strHex);
            } else {
                sb.append(strHex);
            }
        }

        return sb.toString();
    }

    private static byte[] hexStringToByte(String s) {
        byte[] baKeyword = new byte[s.length() / 2];

        for(int i = 0; i < baKeyword.length; ++i) {
            try {
                baKeyword[i] = (byte)(255 & Integer.parseInt(s.substring(i * 2, i * 2 + 2), 16));
            } catch (Exception var4) {
             //   LogUtils.logError(var4);
            }
        }

        return baKeyword;
    }
}
