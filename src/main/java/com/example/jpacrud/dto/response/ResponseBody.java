package com.example.jpacrud.dto.response;

import lombok.Getter;

@Getter
public class ResponseBody {

    private String result;
    private Object data;

    public ResponseBody(String result) {
        this.result = result;
    }

    public ResponseBody(String result, Object data) {
        this.result = result;
        this.data = data;
    }

    public ResponseBody data(Object data) {
        this.data = data;
        return this;
    }

    public static ResponseBody success() {
        return new ResponseBody("success");
    }

    public static ResponseBody fail() {
        return new ResponseBody("fail");
    }

    public static ResponseBody success(Object data) {
        return success().data(data);
    }
}
