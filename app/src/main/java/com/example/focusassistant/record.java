package com.example.focusassistant;

public class record {
    private String type;
    private String time;
    private String finished;

    public record(String type,String time,String finished){
        this.type=type;
        this.time=time;
        this.finished=finished;
    }

    public String getType() {
        return type;
    }

    public String getTime() {
        return time;
    }

    public String getFinished() {
        return finished;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setFinished(String finished) {
        this.finished = finished;
    }
}
