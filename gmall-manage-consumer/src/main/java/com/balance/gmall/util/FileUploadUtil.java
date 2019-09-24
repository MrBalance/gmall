package com.balance.gmall.util;

import org.apache.commons.lang.StringUtils;
import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 上传文件至fastfds服务器工具类
 *
 * @author: yunzhang.du
 * @date: 2019年09月24日
 * @version: v1.0
 * @since: JDK 1.8
 */
public class FileUploadUtil {

    /**
     * 上传文件
     *
     * @param: file 文件对象
     * @return: String[]
     * @throw: IOException
     * @throw: MyException
     * @Date: 2019/9/24 - 11:30
     * @author: yunzhang.du
     */
    public String[] fileUpload(MultipartFile file) throws IOException, MyException {
        String trackerFile = FileUploadUtil.class.getResource("/tracker.conf").getFile();
        ClientGlobal.init(trackerFile);
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getConnection();
        StorageClient storageClient = new StorageClient(trackerServer, null);
        String originalFilename = file.getOriginalFilename();
        String[] uploadFile = null;
        byte[] fileBytes = file.getBytes();
        if (!StringUtils.isBlank(originalFilename)) {
            int pointIndex = originalFilename.lastIndexOf(".") + 1;
            // 文件后缀
            String extName = originalFilename.substring(pointIndex);
            uploadFile = storageClient.upload_file(fileBytes, extName, null);
        }
        return uploadFile;
    }

    public static String imageUpload(MultipartFile file) throws IOException, MyException {
        FileUploadUtil fileUploadUtil = new FileUploadUtil();
        String[] uploadFile = fileUploadUtil.fileUpload(file);
        // ImageUploadResponse image = new ImageUploadResponse();
        StringBuffer url = new StringBuffer("http://192.168.67.130");
        for (int i = 0; i < uploadFile.length; i++) {
            url.append("/").append(uploadFile[i]);
        }
        return url.toString();
    }

    /**
     * 从FastDFS删除文件
     *
     * @param localFilename  本地文件名
     * @param groupName      文件在FastDFS中的组名
     * @param remoteFilename 文件在FastDFS中的名称
     * @return int 0成功 1失败
     */
    public static int deleteFile(String localFilename, String groupName, String remoteFilename) throws IOException, MyException {
        String trackerFile = FileUploadUtil.class.getResource("/tracker.conf").getFile();
        ClientGlobal.init(trackerFile);
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getConnection();
        StorageClient storageClient = new StorageClient(trackerServer, null);
        int i = storageClient.delete_file(groupName, remoteFilename);
        return i;
    }
}
