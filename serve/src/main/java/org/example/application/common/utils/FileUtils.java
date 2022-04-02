package org.example.application.common.utils;

import org.example.application.common.ApplicationException;
import org.example.application.custom.entity.FileInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.UUID;

public class FileUtils {
    private static final Logger logger = LoggerFactory.getLogger(FileUtils.class);

    private final String UPLOAD_PATH;
    private final String VIRTUAL_PATH;

    public FileUtils(String uploadPath, String virtualPath) {
        this.UPLOAD_PATH = uploadPath;
        this.VIRTUAL_PATH = virtualPath;
    }

    public FileInfo saveFile(MultipartFile multipartFile) {
        String uploadPath = UPLOAD_PATH;
        String fullName = UUID.randomUUID().toString().replace("-", "");
        File fold = new File(uploadPath);
        try {
            if (!fold.exists()) {
                if (!fold.mkdirs()) {
                    throw new ApplicationException("文件保存失败: 目录创建失败");
                }
            }
            String originalFilename = multipartFile.getOriginalFilename();
            if (originalFilename != null) {
                int suffixIndex = originalFilename.lastIndexOf(".");
                int length = originalFilename.length();
                String suffix = originalFilename.substring(suffixIndex, length);
                fullName += suffix;
            }
            File file = new File(uploadPath + "/" + fullName.toLowerCase());
            writeFile(multipartFile, file);
            uploadPath = file.getPath();
        } catch (IOException e) {
            return null;
        }
        String virtualPath = VIRTUAL_PATH + fullName;
        return createFileInfo(uploadPath,virtualPath,multipartFile);
    }

    private FileInfo createFileInfo(String uploadPath, String virtualPath, MultipartFile multipartFile) {
        FileInfo fileInfo = new FileInfo();
        fileInfo.setOldName(multipartFile.getOriginalFilename());
        fileInfo.setVirtualPath(virtualPath);
        fileInfo.setUploadPath(uploadPath);
        fileInfo.setFileSize(multipartFile.getSize());
        fileInfo.setContentType(multipartFile.getContentType());
        return fileInfo;
    }
    private static void writeFile(MultipartFile mFile, File file) throws IOException {
        logger.debug("开始写入文件");
        InputStream is = mFile.getInputStream();
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
        if (!file.exists()) {
            if (!file.createNewFile()) {
                throw new ApplicationException("文件写入失败");
            }
        }
        BufferedInputStream bis = new BufferedInputStream(is);
        byte[] b = new byte[1024];
        while (bis.read(b) != -1) {
            bos.write(b);
        }
        bos.flush();
        bos.close();
    }

}
