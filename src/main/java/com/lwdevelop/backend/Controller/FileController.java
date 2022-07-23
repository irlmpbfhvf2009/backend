package com.lwdevelop.backend.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.lwdevelop.backend.utils.FileUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "測試接口")
@RestController
@RequestMapping("/avatar")
public class FileController {

    // 设置上传文件的最大值
    public static final int AVATAR_MAX_SIZE = 10 * 1024 * 1024;// 10MB Byte字节

    // 限制上传文件的类型
    public static final List<String> AVATAR_TYPE = new ArrayList<>();
    // 集合初始化 静态代码块，随着类的加载而加载，只加载一次
    static {
        AVATAR_TYPE.add("image/jpeg");
        AVATAR_TYPE.add("image/png");
        AVATAR_TYPE.add("image/bmp");
        AVATAR_TYPE.add("image/gif");
    }

    @ApiOperation("上傳圖片")
    @PostMapping(path = "/upload")
    public ResponseEntity<String> upload(
            @RequestParam("file") MultipartFile file,
            HttpServletRequest request) throws Exception {

        System.out.println(file.getOriginalFilename());
        String path = ClassUtils.getDefaultClassLoader().getResource("").getPath() + "static/"+file.getOriginalFilename();
        // 设置文件上传，并且设置了新的唯一名字XXXXX.jpg
        FileUtil.upload(file, path, file.getOriginalFilename());

        return ResponseEntity.status(HttpStatus.OK).body("ok");
    }

}
