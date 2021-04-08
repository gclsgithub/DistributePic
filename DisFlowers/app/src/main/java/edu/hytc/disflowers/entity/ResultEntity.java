package edu.hytc.disflowers.entity;

public class ResultEntity {

    private Result result;
    private String logId;

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public String getLogId() {
        return logId;
    }

    public void setLogId(String logId) {
        this.logId = logId;
    }

    public ResultEntity(Result result, String logId) {
        this.result = result;
        this.logId = logId;
    }
}
