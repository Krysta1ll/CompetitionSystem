package csu.krystal.recommsys.common.util;

import lombok.Getter;

@Getter
public enum ResponseEnum {

    SUCCESS(0, "success"),
    ERROR(1, "error");

    final Integer code;

    final String msg;

    ResponseEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
