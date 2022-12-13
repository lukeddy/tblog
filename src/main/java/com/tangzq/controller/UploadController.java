package com.tangzq.controller;

import com.tangzq.interceptor.LoginInterceptor;
import com.tangzq.utils.Constants;
import com.tangzq.utils.UploadUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;


/**
 * 恩建上传控制器
 * @author tangzhiqiang
 */
@Controller
@RequestMapping("/upload")
public class UploadController {

    private final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

    @Value("${upload.files.folder}")
    private String uploadFilesFolder;

    /**
     * 文章图片需要加上上下文路径，所以单独使用上传方法
     * @param response
     * @param file
     */
    @RequestMapping(value="/image",method= RequestMethod.POST)
    public void uploadImg(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "editormd-image-file", required = false) MultipartFile file){
        try {
            String relativeSavePath = UploadUtil.upload(file, Constants.UPLOAD_FOLDER);
            logger.info("Image Saved Path:"+relativeSavePath);
            response.getWriter().write( "{\"success\": 1, \"message\":\"上传成功\",\"url\":\""+request.getContextPath()+ relativeSavePath + "\"}" );
        } catch (Exception e) {
            logger.error("upload image error",e);
            try {
                response.getWriter().write( "{\"success\": 0, \"message\":\"上传失败\",\"url\":\""+ "\"}" );
            } catch (IOException e1) {
                logger.error("response error",e);
            }
        }
    }

    /**
     * 文章缩略图
     * @param response
     * @param file
     */
    @RequestMapping(value="/thumbnail",method= RequestMethod.POST)
    public void uploadThumbImg(HttpServletResponse response, @RequestParam(value = "thumbImage", required = false) MultipartFile file){
        try {
            String relativePath = UploadUtil.upload(file, Constants.UPLOAD_FOLDER);
            logger.info("Saved Path:"+relativePath);
            response.getWriter().write( "{\"success\": 1, \"message\":\"上传成功\",\"url\":\"" +relativePath + "\"}" );
        } catch (Exception e) {
            logger.error("upload image error",e);
            try {
                response.getWriter().write( "{\"success\": 0, \"message\":\"上传失败\",\"url\":\""+ "\"}" );
            } catch (IOException e1) {
                logger.error("response error",e);
            }
        }
    }

}
