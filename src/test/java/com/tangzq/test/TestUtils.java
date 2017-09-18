package com.tangzq.test;

import com.tangzq.utils.UploadUtil;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.junit.Test;

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

}
