package com.tangzq.utils;

import com.google.common.base.Charsets;
import com.google.common.hash.Hasher;
import com.google.common.hash.Hashing;
import com.tangzq.identicon.Identicon;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author luke
 */
public class GravatarUtils {
    private static String hex(byte[] array) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < array.length; ++i) {
            sb.append(Integer.toHexString((array[i]
                    & 0xFF) | 0x100).substring(1,3));
        }
        return sb.toString();
    }
    private static String md5Hex (String message) {
        try {
            MessageDigest md =MessageDigest.getInstance("MD5");
            return hex (md.digest(message.getBytes("CP1252")));
        } catch (NoSuchAlgorithmException e) {
        } catch (UnsupportedEncodingException e) {
        }
        return null;
    }

    public static String makeGravatar(String email){
         return "http://www.gravatar.com/avatar/" + md5Hex(email.toLowerCase()) + "?size=48";
    }

    /**
     * 使用awesome-gravatar生成头像图片
     * @param email
     * @return
     */
    public static String makeLocalAvatar(String email,int size){
        Hasher hasher = Hashing.md5().newHasher();
        hasher.putString(email, Charsets.UTF_8);
        String md5 = hasher.hash().toString();
        BufferedImage image = new Identicon().create(md5, size);
        String relativeSavePath=SysUtils.getRelativeSavePath("tmp.png",Constants.UPLOAD_AVATAR_FOLDER);
        File file = new File(SysUtils.getApplicationDeployPath() + relativeSavePath);
        try {
            ImageIO.write(image, "PNG", file);
            return relativeSavePath;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
