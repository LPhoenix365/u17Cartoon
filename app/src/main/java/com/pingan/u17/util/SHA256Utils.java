package com.pingan.u17.util;

import com.example.framework.http.abutil.AbLogUtil;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * SHA256加密算法
 * @author ex-caowanjiang001
 * 2015年11月27日08:54:07
 */
public class SHA256Utils {
	
	public static String doEncrypt(String str){
		AbLogUtil.i("test", "SHA-256加密 加密明文："+str);
		byte[] bt = str.getBytes();
		String steDes = "";
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(bt);
			steDes = bytes2Hex(md.digest());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		AbLogUtil.i("test", "SHA-256加密加密暗文："+steDes);
		return steDes;
	}
	
    public static String bytes2Hex(byte[] bts) {
        String des = "";
        String tmp = null;
        for (int i = 0; i < bts.length; i++) {
            tmp = (Integer.toHexString(bts[i] & 0xFF));
            if (tmp.length() == 1) {
                des += "0";
            }
            des += tmp;
        }
        return des;
    }

}
