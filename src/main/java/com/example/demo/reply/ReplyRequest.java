package com.example.demo.reply;

import lombok.Data;

public class ReplyRequest {

    @Data
    public static class Save {
        private Integer boardId;
        private String comment;
    }
}
