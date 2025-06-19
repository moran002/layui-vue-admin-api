package com.moran.controller;

import cn.dev33.satoken.annotation.SaIgnore;
import com.moran.conf.bean.ResponseBean;
import com.moran.utils.FileUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 测试
 */
@RestController
@RequestMapping("/test")
public class TestController {

    /**
     * 上传文件
     */
    @PostMapping("/upload")
    @SaIgnore
    public ResponseBean<String> upload(@RequestParam("file") MultipartFile file) {
        String write = FileUtil.write("D:\\java\\moran\\files", file);
        return ResponseBean.ok(write);
    }
}
