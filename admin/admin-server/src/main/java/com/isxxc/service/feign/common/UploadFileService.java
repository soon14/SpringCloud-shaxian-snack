package com.isxxc.service.feign.common;

import com.isxxc.client.UploadFileClient;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.context.annotation.Bean;

import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;

/**
 * @author 泥水佬
 * @date 2017/12/30
 */
@FeignClient(name = "${feign-client.common-server}", configuration = UploadFileService.MultipartSupportConfig.class)
public interface UploadFileService extends UploadFileClient {

    /**
     * 文件内容配置
     */
    class MultipartSupportConfig {
        @Bean
        public Encoder feignFormEncoder() {
            return new SpringFormEncoder();
        }
    }
}
