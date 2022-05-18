package com.xbb.cases.init;

import com.xbb.cases.constant.PathConstant;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;

import javax.annotation.Resource;

@Component
public class InitConfig implements CommandLineRunner {

    @Override
    public void run(String... strings) throws Exception {
        // 项目路径
        PathConstant.WEB_FILE_ROOT = ClassUtils.getDefaultClassLoader()
                .getResource("").getPath().substring(1);
        // 图片存储目录
        PathConstant.FILE_STORAGE_ROOT = PathConstant.WEB_FILE_ROOT + "static/images/";
    }

}
