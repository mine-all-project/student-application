package org.example.application.common;

import lombok.Getter;

public enum ResponseCode {
    SUCCESS(200),
    ERROR(500),
    AUTH_FAIL(401);
    @Getter
    private int code;

    ResponseCode(int code) {
        this.code = code;
    }
}
