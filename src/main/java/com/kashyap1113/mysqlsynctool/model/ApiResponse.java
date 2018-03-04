package com.kashyap1113.mysqlsynctool.model;

import com.google.gson.annotations.SerializedName;

public class ApiResponse<T> {
    public static final String RESULT_SUCCESS = "success";
    public static final String RESULT_FAIL = "fail";
    public static final String RESULT_NO_DATA = "no_data";

    @SerializedName("result")
    private String result;

    @SerializedName("data")
    private T data;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
