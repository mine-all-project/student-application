package org.example.application.common.utils;

import org.example.application.common.ApplicationException;
import org.example.application.system.entity.FileInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.UUID;

public class FileUtils {
    private static final Logger logger = LoggerFactory.getLogger(FileUtils.class);

    private final String UPLOAD_PATH;
    private final String VIRTUAL_PATH;

    /**
     * @param uploadPath 文件保存路径
     */
    public FileUtils(String uploadPath, String virtualPath) {
        this.UPLOAD_PATH = uploadPath;
        this.VIRTUAL_PATH = virtualPath;
    }

    /**
     * 保存文件,返回文件路径
     *
     * @param multipartFile 传入的文件流
     * @return 返回上传的文件信息
     */
    public FileInfo saveFile(MultipartFile multipartFile) {
        String uploadPath = UPLOAD_PATH;
        logger.info("保存文件,路径[{}]", uploadPath);
        String fullName = UUID.randomUUID().toString().replace("-", "");
        logger.info("保存文件,文件名[{}]", fullName);
        File fold = new File(uploadPath);
        try {
            if (!fold.exists()) {
                logger.debug("目录不存在,创建目录[{}]", fold.getPath());
                if (!fold.mkdirs()) {
                    logger.error("文件保存失败: 目录创建失败");
                    throw new ApplicationException("文件保存失败: 目录创建失败");
                }
            }
            String originalFilename = multipartFile.getOriginalFilename();
            if (originalFilename != null) {
                int suffixIndex = originalFilename.lastIndexOf(".");
                int length = originalFilename.length();
                String suffix = originalFilename.substring(suffixIndex, length);
                fullName += suffix;
                logger.debug("生成全文件名[{}],文件后缀为:[{}]", fullName, suffix);
            }
            File file = new File(uploadPath + "/" + fullName.toLowerCase());
            logger.info("准备写入文件");
            writeFile(multipartFile, file);
            logger.info("文件写入完成");
            uploadPath = file.getPath();
        } catch (IOException e) {
            logger.error("保存文件时出现错误[]", e);
            return null;
        }
        String virtualPath = VIRTUAL_PATH + fullName;
        logger.info("文件保存完成,路径[{}]", uploadPath);
        logger.info("文件保存完成,虚拟路径[{}]", virtualPath);
        return createFileInfo(uploadPath,virtualPath,multipartFile);
    }

    /**
     * 生成文件信息
     * @param uploadPath 文件真实保存路径
     * @param virtualPath 文件虚拟访问路径
     * @param multipartFile 上传的文件信息
     * @return 生成的文件信息
     */
    private FileInfo createFileInfo(String uploadPath, String virtualPath, MultipartFile multipartFile) {
        FileInfo fileInfo = new FileInfo();
        fileInfo.setOldName(multipartFile.getOriginalFilename());
        fileInfo.setVirtualPath(virtualPath);
        fileInfo.setUploadPath(uploadPath);
        fileInfo.setFileSize(multipartFile.getSize());
        fileInfo.setContentType(multipartFile.getContentType());
        return fileInfo;
    }
    /**
     * 写入文件
     *
     * @param mFile 传入的文件
     * @param file  需要保存到的文件
     * @throws IOException 文件IO异常
     */
    private static void writeFile(MultipartFile mFile, File file) throws IOException {
        logger.debug("开始写入文件");
        InputStream is = mFile.getInputStream();
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
        if (!file.exists()) {
            if (!file.createNewFile()) {
                logger.error("文件写入失败,文件创建失败");
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
        logger.debug("文件写入完成");
    }

    /**
     * 根据地址获取名字
     *
     * @param path 文件地址
     * @return 文件名
     */
    public static String getNameByPath(String path) {
        String fileName = "/file" + path.split("file")[1];
        System.out.println("文件名为: " + fileName);
        return fileName;
    }
}
