package com.songbo.filecoincloud.utils;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ImagesUtil
 * @Description TODO
 * @Author songbo
 * @Date 2020/2/12 下午6:04
 **/
@Slf4j
public class ImagesUtil {

    public static List<String> saveImagesList(List<MultipartFile> fileList, String dir) throws Exception {

        List<String> names = new ArrayList<>();
        for (MultipartFile file : fileList) {
            //判断图片格式
            String name = file.getOriginalFilename();
            if (!name.matches("(.*).(jpg|bmp|gif|ico|pcx|jpeg|tif|png|raw|tga)$")) {
                log.error("上传图片格式不正确： {}", name);
                throw new Exception("上传图片格式不正确");
            }

            File f = new File(dir+name);
            f.getParentFile().mkdir();
            try {
                file.transferTo(f);
            } catch (IOException e) {
                log.error("transferTo_IOException： {}", e);
                throw new IOException(e);
            }
            names.add(dir+name);
        }
        return names;
    }

    public static void main(String[] args) {
        String name = "asd.png";
        if (name.matches("(.*).(jpg|bmp|gif|ico|pcx|jpeg|tif|png|raw|tga)$")) {
            System.out.println("yes");
        } else {
            System.out.println("no");
        }
    }
}
