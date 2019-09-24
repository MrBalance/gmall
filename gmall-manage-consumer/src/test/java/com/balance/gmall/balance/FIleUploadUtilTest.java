package com.balance.gmall.balance;

import com.balance.gmall.util.FileUploadUtil;
import org.apache.commons.lang.StringUtils;
import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;

import java.io.IOException;

/**
 * @author: yunzhang.du
 * @date: 2019年09月24日
 * @version: v1.0
 * @since: JDK 1.8
 */
public class FIleUploadUtilTest {
    public static void main(String[] args) throws IOException, MyException {
        String trackerFile = FileUploadUtil.class.getResource("/tracker.conf").getFile();
        ClientGlobal.init(trackerFile);
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getConnection();
        StorageClient storageClient = new StorageClient(trackerServer, null);
        String originalFilename = "C:/Users/kenfor/Desktop/fc761817b2f276e4.jpg";
        String[] uploadFile = null;
        if (!StringUtils.isBlank(originalFilename)) {
            int pointIndex = originalFilename.lastIndexOf(".") + 1;
            // 文件后缀
            String extName = originalFilename.substring(pointIndex);
            uploadFile = storageClient.upload_file(originalFilename, extName, null);
            for (String s : uploadFile) {
                System.out.println(s);
            }
        }
    }
}
