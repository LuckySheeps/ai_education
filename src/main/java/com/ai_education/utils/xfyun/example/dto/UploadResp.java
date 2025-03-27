package com.ai_education.utils.xfyun.example.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UploadResp extends ResponseMsg {
    private Datas data;

    @Data
    public static class Datas{
        private String originPath;
        private String filePath;
        private String fileId;
    }
}
