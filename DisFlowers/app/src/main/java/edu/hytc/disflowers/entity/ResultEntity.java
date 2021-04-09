package edu.hytc.disflowers.entity;

import java.util.List;

public class ResultEntity {

    private List<Result> result;
    private String logId;

    public List<Result> getResult() {
        return result;
    }

    public void setResult(List<Result> result) {
        this.result = result;
    }

    public String getLogId() {
        return logId;
    }

    public void setLogId(String logId) {
        this.logId = logId;
    }

    public ResultEntity(List<Result> result, String logId) {
        this.result = result;
        this.logId = logId;
    }
}
