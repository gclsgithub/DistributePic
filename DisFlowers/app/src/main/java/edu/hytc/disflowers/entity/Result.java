package edu.hytc.disflowers.entity;

public class Result {

    private String score;

    private String name;

    private BaikeInfo baike_info;

    public Result(String score, String name, BaikeInfo baikeInfo) {
        this.score = score;
        this.name = name;
        this.baike_info = baikeInfo;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BaikeInfo getBaike_info() {
        return baike_info;
    }

    public void setBaike_info(BaikeInfo baike_info) {
        this.baike_info = baike_info;
    }
}
