package ru.alexey.site.model;
/* 
08.03.2022: Alexey created this file inside the package: ru.alexey.site.model 
*/

import java.util.Date;

public class ApiError {
    private int code;
    private String message;
    private Date time;

    public ApiError(int code, String message, Date time) {
        this.code = code;
        this.message = message;
        this.time = time;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
