package edu.hytc.disflowers.entity;

public class BaikeInfo {

    private String image_url;

    private String baike_url;

    private String description;

    public BaikeInfo(String image_url, String baike_url, String description) {
        this.image_url = image_url;
        this.baike_url = baike_url;
        this.description = description;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getBaike_url() {
        return baike_url;
    }

    public void setBaike_url(String baike_url) {
        this.baike_url = baike_url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
