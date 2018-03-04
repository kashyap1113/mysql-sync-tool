package com.kashyap1113.mysqlsynctool.model.dto;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Tables {
    @SerializedName("tables")
    List<String> tables;

    public Tables() {
        super();
    }

    public Tables(List<String> tables) {
        super();
        this.tables = tables;
    }

    public List<String> getTables() {
        return tables;
    }

    public void setTables(List<String> tables) {
        this.tables = tables;
    }
    
}
