package org.heg.model.exception;

import java.util.ArrayList;
import java.util.List;

public class ErrorMessage {

    private Integer statusCode;

    private String type;

    private String title;

    private String detail;

    private List<String> errors = new ArrayList<>();

    public ErrorMessage(Integer statusCode, String type, String title, String detail) {
        this.statusCode = statusCode;
        this.type = type;
        this.title = title;
        this.detail = detail;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
