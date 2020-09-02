package org.blog.api.web.payload;

/**
 * Created by melchor
 * Date: 2020/09/02
 * Time: 4:28 PM
 */
public enum ResultStatus {

    SUCCESS("2000", "request success."),
    BAD_REQUEST("4000", "bad request");

    private final String code;
    private final String message;

    ResultStatus(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
