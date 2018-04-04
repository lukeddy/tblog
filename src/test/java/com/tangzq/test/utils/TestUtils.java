package com.tangzq.test.utils;

import com.talanlabs.avatargenerator.Avatar;
import com.talanlabs.avatargenerator.GitHubAvatar;
import com.talanlabs.avatargenerator.IdenticonAvatar;
import com.talanlabs.avatargenerator.cat.CatAvatar;
import com.talanlabs.avatargenerator.layers.backgrounds.RandomColorPaintBackgroundLayer;
import com.talanlabs.avatargenerator.layers.masks.RoundRectMaskLayer;
import com.talanlabs.avatargenerator.layers.others.ShadowLayer;
import com.talanlabs.avatargenerator.smiley.SmileyAvatar;
import com.tangzq.utils.DateUtils;
import com.tangzq.utils.GravatarUtils;
import com.tangzq.utils.UploadUtil;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.Test;

import java.io.File;
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

    @Test
    public void testGenerateAvatar(){
        File file=new File("/tmp/github-"+System.currentTimeMillis()+".png");
        Avatar avatar = GitHubAvatar.newAvatarBuilder().build();
        avatar.createAsPngToFile(123456L,file);

        File file2=new File("/tmp/smile-"+System.currentTimeMillis()+".png");
        Avatar avatar2 = SmileyAvatar.newEyeMouthAvatarBuilder().build();
        avatar2.createAsPngToFile(12345L,file2);

        File file3=new File("/tmp/iden-"+System.currentTimeMillis()+".png");
        Avatar avatar3 = IdenticonAvatar.newAvatarBuilder().build();
        avatar3.createAsPngToFile(123456L,file3);

        File file4=new File("/tmp/cat-"+System.currentTimeMillis()+".png");
        Avatar avatar4 = CatAvatar.newAvatarBuilder()
                .layers(new ShadowLayer(), new RandomColorPaintBackgroundLayer(), new RoundRectMaskLayer())
                .padding(8).margin(8).build();
        avatar4.createAsPngToFile(123456L,file4);
    }

}
