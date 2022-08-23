package com.nxg.upload.config;

import com.github.tobato.fastdfs.FdfsClientConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author nxg
 * date 2022/3/8
 * @apiNote
 */
@Configuration
@Import(FdfsClientConfig.class)
public class FastDfsConfig {
}
