package com.cary.stq.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;


public class MD5Encoder {
    private static final String MD5_ALGORITHM_NAME = "MD5";
    private static final String CHARACTER_SET = "UTF-8";
    private static final int PWD_LEN = 6; // random password length

    /**
     * Encoder by MD5 algorithm
     *
     * @param str
     * @return
     */
    public static String getMD5Str(String str) {
        if (StringUtils.isEmpty(str)) {
            throw new IllegalArgumentException("The String is null, can not encoder in MD5");
        }

        byte[] byteArray = digestToByteArray(str);
        return toHexString(byteArray);
    }

    private static byte[] digestToByteArray(String str) {
        byte[] byteArray = null;
        if (!StringUtils.isEmpty(str)) {
            MessageDigest messageDigest = null;
            try {
                messageDigest = MessageDigest.getInstance(MD5_ALGORITHM_NAME);
                messageDigest.reset();
                messageDigest.update(str.getBytes(CHARACTER_SET));
            } catch (NoSuchAlgorithmException e) {
                // logger.error("NoSuchAlgorithmException caught!");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            byteArray = messageDigest.digest();
        }

        return byteArray;
    }

    private static String toHexString(byte[] byteArray) {
        StringBuffer md5StrBuff = new StringBuffer();
        if (ObjectUtil.isNotEmpty(byteArray)) {
            for (int i = 0; i < byteArray.length; i++) {
                if (Integer.toHexString(0xFF & byteArray[i]).length() == 1) {
                    md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
                } else {
                    md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
                }
            }
        }
        return md5StrBuff.toString();
    }

    /**
     * generate random password
     *
     * @return
     * @author
     */
    public static String generateRandomPassword() {
        Random random = new Random();
        StringBuilder strBuilder = new StringBuilder();
        for (int i = 0; i < PWD_LEN; i++) {
            strBuilder.append(random.nextInt(10));
        }
        return strBuilder.toString();
    }

    public static void main(String[] args) {
        System.out.println(MD5Encoder.getMD5Str("1"));
    }
}
