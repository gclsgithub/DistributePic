package edu.hytc.disflowers.entity;

public class BaikeInfo {

    private String baikeUrl;

    private String description;

    public String getBaikeUrl() {
        return baikeUrl;
    }

    public void setBaikeUrl(String baikeUrl) {
        this.baikeUrl = baikeUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BaikeInfo(String baikeUrl, String description) {
        this.baikeUrl = baikeUrl;
        this.description = description;
    }
}
