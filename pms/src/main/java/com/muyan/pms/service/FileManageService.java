package com.muyan.pms.service;

/**
 * @program: FileManageService
 * @description:
 * @author: muyan
 * @date: 2023/07/21 11:40:47
 **/
public interface FileManageService {

    /**
     * 查看文件属性
     * @return
     */
    String findProperties();

    /**
     * 删除文件
     */
    void deleteFile();

    /**
     * 移动文件
     */
    void moveFile();

    /**
     * 复制文件
     */
    void CopeyFile();
}
