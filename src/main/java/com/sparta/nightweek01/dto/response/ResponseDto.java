package com.sparta.nightweek01.dto.response;

public class ResponseDto<T> {
    private boolean success;
    private String message;
    private T data;
    private Error error;

    public ResponseDto(boolean success, String message, T data, Error error) {
        this.success = success;
        this.message = message;
        this.data = data;
        this.error = error;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    public Error getError() {
        return error;
    }

    public static <T> ResponseDto<T> success(String message, T data){
        return new ResponseDto<>(true, message, data, null);
    }

    public static <T> ResponseDto<T> fail(String code, String message){
        return new ResponseDto<>(false, null, null, new Error(code, message));
    }

    public static class Error{
        private String code;
        private String message;

        public Error(String code, String message) {
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
}
