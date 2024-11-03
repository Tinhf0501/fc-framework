package com.fcm.common.constant;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum CommonErrorCode implements IErrorCode {
    SUCCESS("00", "Success", 200),
    VALIDATE_FAIL("01", "Đầu vào không hợp lệ: %s", 400),
    NOT_FOUND_RECORD("02", "Không tìm thấy bản ghi tương ứng", 400),
    INTERNAL_ERROR("99","Lỗi hệ thống", 500),;

    private final String code;
    private final String message;
    private final int httpStatus;
}