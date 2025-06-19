package com.moran.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class FileUtil {

    public static String write(String target, MultipartFile file) {
        String filename = file.getOriginalFilename();
        if (filename == null || filename.isEmpty()) {
            throw new RuntimeException("文件名不能为空");
        }
        File newFile = new File(target, filename);
        if (!newFile.getParentFile().exists()) {
            boolean b = newFile.getParentFile().mkdirs();
            if (!b) {
                throw new RuntimeException("目录无法创建, 可能没有权限. 目录地址:" + newFile.getParentFile());
            }
        }
        try {
            file.transferTo(newFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return newFile.getPath();
    }
}
