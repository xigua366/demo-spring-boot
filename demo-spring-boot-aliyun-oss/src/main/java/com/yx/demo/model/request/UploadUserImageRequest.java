package com.yx.demo.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author yangxi
 * @version 1.0
 */
public class UploadUserImageRequest {

    @JsonProperty("image_name")
    private String imageName;

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
}