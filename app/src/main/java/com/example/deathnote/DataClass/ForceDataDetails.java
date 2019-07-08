package com.example.deathnote.DataClass;

import com.google.gson.annotations.SerializedName;

public class ForceDataDetails {

    @SerializedName("description")
    private String description;
    @SerializedName("url")
    private String url;
    @SerializedName("telephone")
    private String telephone;
    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;

    public ForceDataDetails(String description, String url, String telephone, String id, String name) {
        this.description = description;
        this.url = url;
        this.telephone = telephone;
        this.id = id;
        this.name = name;
    }

    public String getDescription() {
        return description;
    }
    public String getUrl() {
        return url;
    }
    public String getTelephone() {
        return telephone;
    }
    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
}
