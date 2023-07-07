package com.muyan.pms.service;

import java.io.File;

/**
 * @program: DownloadFileService
 * @description: 文件下载
 * @author: muyan
 * @date: 2023/07/20 08:12:20
 **/
public interface DownloadFileService {

    File downloadFile(String fileName);


}
