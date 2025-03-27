package com.ai_education.utils.xfyun.example.dto;

import lombok.Data;

/**
 * ResponseMsg
 *
 * @author ydwang16
 * @version 2023/09/06 14:11
 **/
@Data
public class ResponseMsg {
    private boolean flag;
    private int code;
    private String desc;
    private String sid;

    public boolean success() {
        return code == 0;
    }
}