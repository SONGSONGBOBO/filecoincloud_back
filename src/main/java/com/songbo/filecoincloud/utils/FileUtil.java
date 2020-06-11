package com.songbo.filecoincloud.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @ClassName FileUtil
 * @Description TODO
 * @Author songbo
 * @Date 2020/3/13 下午2:14
 **/
public class FileUtil {


        /**
         *
         * @param file 文件
         * @param path   文件存放路径
         *
         * @return
         */
        public static String upload(MultipartFile file, String path) throws IOException {

            String name = UUID.randomUUID().toString().replaceAll("-","")  + file.getOriginalFilename();
            // 生成新的文件名
            String realPath = path + "/" + name;

            //使用原文件名
            // String realPath = path + "/" + fileName;

            File dest = new File(realPath);

            //判断文件父目录是否存在
            if(!dest.getParentFile().exists()){
                dest.getParentFile().mkdir();
            }

            try {
                //保存文件
                file.transferTo(dest);
                return realPath;
            } catch (IOException e) {
                e.printStackTrace();
                throw new IOException("IO写入失败" +name ,e);
            }

        }

        public static String[] getFileNameListByPath(String path) throws Exception {

            File file = new File(path);
            if (!file.getParentFile().exists()) {
                throw new Exception("文件目录不正确");
            }
            return file.list();
        }

    public static void deleteFile(String path) throws Exception {

        File file = new File(path);
        if (!file.getParentFile().exists()) {
            throw new Exception("文件目录不正确");
        }
        if (!file.delete()){
            throw new Exception("删除失败");
        }

    }


}
