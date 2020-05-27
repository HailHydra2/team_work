package com.fzu.teamwork.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

//加密器
public class Encryptor {
    static private BASE64Decoder decoder = new BASE64Decoder();
    static private BASE64Encoder encoder = new BASE64Encoder();

    //加密
    static public String encrypt(String str){
        return new String(encoder.encode(str.getBytes()));
    }

    //解密
    static public String decrypt(String str){
        String decodeMessage = "";
        try {
            decodeMessage = new String(decoder.decodeBuffer(str));
        }catch (Exception e){
            e.printStackTrace();
        }
        return decodeMessage;
    }
}
