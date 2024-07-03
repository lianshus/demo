package com.student.system.util;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * @Author: wangzy
 */
public class Md5Util {
    /**
     * @author Bean_bag
     * @description 进行Hash运算
     *
     * @param input 参数字符串
     * @return 生成的hash值
     */
    public static String generateHash(String input){
        try {
            //参数校验
            if (null == input) {
                return null;
            }
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(input.getBytes());
            byte[] digest = md.digest();
            BigInteger bi = new BigInteger(1, digest);
            String hashText = bi.toString(16);
            while(hashText.length() < 32){
                hashText = "0" + hashText;
            }
            return hashText;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 加密解密算法 执行一次加密，两次解密
     */
    public static String convertMD5(String inStr){
        if(inStr==null){
            return "";
        }
        char[] a = inStr.toCharArray();
        for (int i = 0; i < a.length; i++){
            a[i] = (char) (a[i] ^ 't');
        }
        String s = new String(a);
        return s;

    }
    public static void main(String[] args) {
        }
}
