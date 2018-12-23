package com.tangzq.controller.api;

import com.tangzq.interceptor.LoginInterceptor;
import com.tangzq.response.Result;
import com.tangzq.utils.Constants;
import com.tangzq.utils.UploadUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


/**
 * 上传文件接口
 * @author tangzhiqiang
 */
@RestController
@RequestMapping("/api/upload")
@Api(value = "上传文件API", description = "文件上传接口",tags = "Upload")
public class ApiUploadController {

    private final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

    @ApiOperation(value="上传文件", notes="文件上传接口")
    @RequestMapping(value="/file",method= RequestMethod.POST,headers = "content-type=multipart/form-data")
    public Result uploadFile(
            @ApiParam(value = "上传的文件", required = true) @RequestParam(value = "file") MultipartFile file){
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
