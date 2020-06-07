package com.songbo.filecoincloud.controller.admin;


import com.alibaba.fastjson.JSONObject;
import com.songbo.filecoincloud.utils.CommonUtil;
import com.songbo.filecoincloud.utils.FileUtil;
import com.songbo.filecoincloud.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * @ClassName UploadController
 * @Description TODO
 * @Author songbo
 * @Date 2020/6/5 下午2:59
 **/
@RestController
@RequestMapping("/admin/upload")
@Api(value = "上传图片接口", tags = {"上传图片接口"})
public class UploadController {

    @PostMapping("/insertImgs")
    @ApiOperation(value = "上传图片，可多张上传", notes = "@MultipartRequest")
    public ResultUtil insertImgs(MultipartRequest file){
        try {
            List<MultipartFile> files = file.getFiles("file");
            for (MultipartFile f : files) {
                try {
                    FileUtil.upload(f, CommonUtil.IMGS_URL);
                } catch (IOException e) {
                    return ResultUtil.result500(e.getMessage(), f.getOriginalFilename());
                }
            }

            return ResultUtil.result200("success", null);
        } catch (Exception e) {
            return ResultUtil.result500("fail",e.getMessage());
        }

    }

    @GetMapping("/getImgs")
    @ApiOperation(value = "获取所有图片")
    public ResultUtil getImgs(){
        try {
            String[] fileNameListByPath = FileUtil.getFileNameListByPath(CommonUtil.IMGS_URL);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("fileList", fileNameListByPath);
            jsonObject.put("path", CommonUtil.IMGS_URL+"/");
            return ResultUtil.result200("success", jsonObject);
        } catch (Exception e) {
            return ResultUtil.result500(e.getMessage(),CommonUtil.IMGS_URL);
        }
    }

    @GetMapping("/deleteImgs")
    @ApiOperation(value = "删除图片s")
    public ResultUtil deleteImgs(@RequestParam("imgs") @ApiParam("图片数组") String imgs){
        try {
            List<String> list = Collections.singletonList(imgs);
            for (String s : list) {
                FileUtil.deleteFile(s);
            }
            return ResultUtil.result200("success", list);
        } catch (Exception e) {
            return ResultUtil.result500(e.getMessage(),e.getMessage());
        }
    }

}
