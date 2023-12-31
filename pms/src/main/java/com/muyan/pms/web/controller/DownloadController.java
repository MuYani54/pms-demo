package com.muyan.pms.web.controller;

import com.muyan.pms.entity.rest.Result;
import com.muyan.pms.service.DownloadFileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @program: DownloadController
 * @description: 文件下载
 * @author: muyan
 * @date: 2023/07/20 09:18:00
 **/

@Api(value = "文件下载接口",description = "文件下载相关的接口")
@RestController
@RequestMapping("/download")
public class DownloadController {

    @Resource(name="fileService")
    private DownloadFileService downloadFileService;

    @GetMapping
    @ApiOperation(value = "文件下载",notes = "文件下载")
    public Result downloadFile(){
        downloadFileService.downloadFile("banner.txt");
        return Result.success();
    }
}
