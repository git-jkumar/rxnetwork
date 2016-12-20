package com.network.rx;

public class RetroError {

    private final int httpErrorCode;
    private final String errorMessage;
    private final RetrofitException.Kind kind;

    RetroError(RetrofitException.Kind kind, String errorMessage, int httpErrorCode) {
        this.httpErrorCode = httpErrorCode;
        this.kind = kind;
        this.errorMessage = errorMessage;
    }


    public int getHttpErrorCode() {
        return httpErrorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public RetrofitException.Kind getKind() {
        return kind;
    }
}