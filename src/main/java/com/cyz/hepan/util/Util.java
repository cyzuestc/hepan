package com.cyz.hepan.util;

import com.alibaba.fastjson.JSON;
import com.cyz.hepan.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.mail.MessagingException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

public class Util {
    @Autowired
    ProductService productService;
    public static List work()  {
        StringBuilder s = new StringBuilder();
        s.append("http://bbs.uestc.edu.cn/mobcent/app/web/index.php?r=forum/topiclist");
        s.append("&accessToken=3178779b80c3ab7b29916a42dcf66");
        s.append("&accessSecret=f155d9573cb5a8ae260fb6dd8c9d5");
        s.append("&forumType=7");
        s.append("&boardId=61");//二手板块
        URL url;
        URLConnection connection;
        BufferedReader in;
        String line;
        StringBuilder sb;
        Map map;
        List<Map> list = null;
        try {
            url = new URL(s.toString());
            connection = url.openConnection();
            connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
            connection.connect();
            in =  new BufferedReader(new InputStreamReader(connection.getInputStream()));
            sb = new StringBuilder();
            while ((line =in.readLine())!=null){
                sb.append(line);
            }
            map = JSON.parseObject(sb.toString());
            list = (List) map.get("list");
            in.close();
            connection = null;
            sb = null;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static String getAppHashValue() throws NoSuchAlgorithmException {
        String timeString = String.valueOf(System.currentTimeMillis());
        String authkey = "appbyme_key";
        String authString = timeString.substring(0, 5) + authkey;
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] hashkey = md.digest(authString.getBytes());
        return new BigInteger(1, hashkey).toString(16).substring(8, 16);//16进制转换字符串
    }

}
