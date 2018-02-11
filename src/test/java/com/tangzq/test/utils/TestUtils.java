package com.tangzq.test.utils;

import com.tangzq.utils.DateUtils;
import com.tangzq.utils.GravatarUtils;
import com.tangzq.utils.UploadUtil;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestUtils {

    @Test
    public void testUploadMethod(){
        try {
            long start = System.currentTimeMillis();
            UploadUtil.saveImgFromURL("https://pic.sosobtc.com/attachment/article/20170918/1505731975111778.png", "/tmp/" + RandomStringUtils.randomNumeric(16) + ".jpg");
            System.out.println("time cost=" + (System.currentTimeMillis() - start) + " ms");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDateUtils() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:m:s");
        Date date = format.parse("2018-02-11 20:48:10");
        System.out.println(DateUtils.getFriendlyTime(date));
    }

    @Test
    public void testGravatar(){
        String headerURL= GravatarUtils.makeGravatar("23424aaa@gmail.com");
        System.out.println(headerURL);
    }

}
