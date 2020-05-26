package com.example.izeat.Utils;

import androidx.annotation.Nullable;

public class SignUpException extends Exception {
    private int exceptionCode;
    private String message;
    public SignUpException(int exceptionCode, String message){
        this.exceptionCode = exceptionCode;
        this.message = message;
    }

    public int getExceptionCode() {
        return exceptionCode;
    }

    @Nullable
    @Override
    public String getMessage() {
        return message;
    }
}
