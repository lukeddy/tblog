package com.tangzq.utils;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.UUID;

/**
 * 系统工具类
 * @author tangzhiqiang
 */
public class SysUtils {

    /**
     * 获取保存文件的位置,JAR所在目录的路径加上保存文件夹
     * @return
     */
    public static String getApplicationDeployPath() {
        File file = new File("");
        return file.getAbsolutePath() + "/";
    }


    /**
     * 上传文件目录绝对路径
     * @return
     */
    public static String getAbsolutePathOfUploadFolder(){
        return getApplicationDeployPath()+Constants.UPLOAD_FOLDER+"/";
    }


    /**
     * 去的类似/xxx/xxx/2018/03/abc.jpg的文件保存路径
     * @param filename
     * @param saveFolder
     * @return
     */
    public static String getRelativeSavePath(String filename,String saveFolder) {
        String prefix = "/"+saveFolder+"/" + DateFormatUtils.format(new Date(), "yyyy/MM");
        if (!new File(getApplicationDeployPath() + prefix).exists()) {
            new File(getApplicationDeployPath() + prefix).mkdirs();
        }

        filename = StringUtils.trimToNull(filename);
        String uuid= UUID.randomUUID().toString().replaceAll("-","");
        if (filename == null) {
            return prefix + "/" +uuid + "." + null;
        } else {
            filename = filename.replace('\\', '/');
            filename = filename.substring(filename.lastIndexOf("/") + 1);
            int index = filename.lastIndexOf(".");
            String ext = null;
            if (index >= 0) {
                ext = StringUtils.trimToNull(filename.substring(index + 1));
            }
            return prefix + "/" + uuid + "." + (ext == null ? null : (ext));
        }
    }

}
