package com.myserv.api.rh.configfile;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component("imageStorageProperties")
@ConfigurationProperties(prefix = "image")
public class ImageStorageProperties {
    private String uploadImageDir;

    public String getImageDir() {
        return uploadImageDir;
    }

    public void setImageDir(String uploadImageDir) {
        this.uploadImageDir = uploadImageDir;
    }
}
