package com.kashyap1113.mysqlsynctool.model.dto;

public class IdValue {
    private int id;
    private String value;
    public IdValue() {
        super();
    }
    public IdValue(int id, String value) {
        super();
        this.id = id;
        this.value = value;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
    
}
