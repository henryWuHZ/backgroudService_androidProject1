package com.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;


public class MD5 {
    /**

     * 将指定的字符串用MD5加密

     * @param originstr 需要加密的字符串

     * @return 加密后的字符串

     */
	static int cutLength=8;
	static int beginCut=5;
	
    public static String ecodeByMD5(String originstr) throws NoSuchAlgorithmException, UnsupportedEncodingException {

        String result = null;

        char hexDigits[] = {//用来将字节转换成 16 进制表示的字符

                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

        if(originstr != null){

            try {

                //返回实现指定摘要算法的 MessageDigest 对象

                MessageDigest md = MessageDigest.getInstance("MD5");

                //使用utf-8编码将originstr字符串编码并保存到source字节数组

                byte[] source = originstr.getBytes("utf-8");

                //使用指定的 byte 数组更新摘要

                md.update(source);

                //通过执行诸如填充之类的最终操作完成哈希计算，结果是一个128位的长整数

                byte[] tmp = md.digest();

                //用16进制数表示需要32位

                char[] str = new char[32];

                for(int i=0,j=0; i < 16; i++){

                    //j表示转换结果中对应的字符位置

                    //从第一个字节开始，对 MD5 的每一个字节

                    //转换成 16 进制字符

                    byte b = tmp[i];

                    //取字节中高 4 位的数字转换

                    //无符号右移运算符>>> ，它总是在左边补0

                    //0x代表它后面的是十六进制的数字. f转换成十进制就是15

                    str[j++] = hexDigits[b>>>4 & 0xf];

                    // 取字节中低 4 位的数字转换

                    str[j++] = hexDigits[b&0xf];

                }

                result = new String(str);//结果转换成字符串用于返回

            } catch (NoSuchAlgorithmException e) {

                //当请求特定的加密算法而它在该环境中不可用时抛出此异常

                e.printStackTrace();

            } catch (UnsupportedEncodingException e) {

                //不支持字符编码异常

                e.printStackTrace();

            }

        }
       
        return doImprovement(result);

    }
    //模糊化处理
    public static String doImprovement(String preString){
    	String randomData = Integer.toHexString(new Random().nextInt());
    	StringBuffer temp = new StringBuffer();
    	temp.append(preString.substring(0, beginCut));
    	temp.append(randomData);
    	temp.append(preString.substring(beginCut+cutLength,preString.length()));
    	System.out.println(temp);
    	return temp.toString();
    }

    //使用MD5检查密码

    public boolean checkPWD(String typPWD, String relPWD) throws UnsupportedEncodingException, NoSuchAlgorithmException {

        if(ecodeByMD5(typPWD).equals(ecodeByMD5(relPWD))){

            return true;

        }

        return false;

    }
    //消息模糊匹配
    public static boolean checkMSG(String preMsg,String relMsg) {
		if (preMsg.substring(0,beginCut).equals(relMsg.substring(0,beginCut))
				&&preMsg.substring(beginCut+cutLength,preMsg.length()).equals(relMsg.substring(beginCut+cutLength,relMsg.length()))) {
			return true;
		}else{
			return false;
		}
	}
}
