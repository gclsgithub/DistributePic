package edu.hytc.disflowers.entity;

public class Result {

    private String score;

    private String name;

    private BaikeInfo baikeInfo;

    public Result(String score, String name, BaikeInfo baikeInfo) {
        this.score = score;
        this.name = name;
        this.baikeInfo = baikeInfo;
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

    public BaikeInfo getBaikeInfo() {
        return baikeInfo;
    }

    public void setBaikeInfo(BaikeInfo baikeInfo) {
        this.baikeInfo = baikeInfo;
    }
}
