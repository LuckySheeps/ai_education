package com.ai_education.utils.xfyun.example.dto;

import lombok.Data;

import java.util.List;

@Data
public class FileStatusResp {
    private int code;
    private String sid;
    private String desc;
    private List<FileStatusData> data;
}
