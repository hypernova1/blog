package org.blog.api.web.payload;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by melchor
 * Date: 2020/09/02
 * Time: 4:25 PM
 */
@Getter
@Setter
public class ResponseDto<T> {
    private String code;
    private String message;
    private T data;

    public ResponseDto(ResultStatus resultStatus, T data) {
        this.code = resultStatus.getCode();
        this.message = resultStatus.getMessage();
        this.data = data;
    }

    public ResponseDto<T> of(ResultStatus resultStatus, T data) {
        return new ResponseDto<>(resultStatus, data);
    }

}
