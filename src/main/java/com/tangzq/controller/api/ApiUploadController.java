package com.tangzq.controller.api;

import com.tangzq.interceptor.LoginInterceptor;
import com.tangzq.response.Result;
import com.tangzq.utils.Constants;
import com.tangzq.utils.UploadUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


/**
 * 上传文件接口
 * @author luke
 */
@RestController
@RequestMapping("/api/upload")
@Tag(name = "上传文件API", description = "文件上传接口")
public class ApiUploadController {

    private final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

    @Operation(summary="上传文件", description="文件上传接口")
    @RequestMapping(value="/file",method= RequestMethod.POST,headers = "content-type=multipart/form-data")
    public Result uploadFile(@RequestParam(value = "file") MultipartFile file){
        try {
            String relativeSavePath = UploadUtil.upload(file, Constants.UPLOAD_FOLDER);
            logger.info("Image Saved Path:"+relativeSavePath);
            return Result.ok("上传成功！",relativeSavePath);
        } catch (Exception e) {
            logger.error("upload image error",e);
            return Result.fail("上传失败");
        }
    }

}
