package com.sg.zhsd.uav.controller;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.extension.api.R;
import com.sg.bdyw.web.BaseController;
import com.sg.zhsd.uav.service.IFileService;
import com.sg.zhsd.uav.utils.model.ModelListener;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Api(tags = {"文件资源管理"})
@RestController
@RequestMapping("/sincere/file")
@CrossOrigin(allowCredentials = "true") //允许跨域请求
@EnableAutoConfiguration
public class FilController extends BaseController {


    @Autowired
    IFileService fileService;

    @ApiOperation(value = "文件上传")
    @PostMapping("upload")
    public R uploadFile(MultipartFile file) throws IOException {

        return R.ok(fileService.uploadFile(file));
    }


    @ApiOperation(value = "文件下载")
    @GetMapping("download")
    public R uploadFile(String fileId, HttpServletResponse response) throws IOException {

        return R.ok(fileService.downLoadFile(fileId,response));
    }


    @ApiOperation(value = "资源文件列表获取")
    @GetMapping("file-list")
    public R getFileList() throws IOException {

        return R.ok(fileService.getFileList());
    }


    @ApiOperation(value = "excel表导入")
    @PostMapping(value = "/importExcel")
    R importExcel(MultipartFile file) {

        return R.ok(fileService.importExcel(file));
    }

}
