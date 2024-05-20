package com.example.manager.authentication.error;


import com.example.manager.util.exception.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Arrays;

public enum ErrorCodes implements ErrorCode {

    SYSTEM_FAILURE(-1, "ErrorCodes.SYSTEM_FAILURE", HttpStatus.INTERNAL_SERVER_ERROR),
    THIS_USER_DOES_NOT_EXIST(100, "ErrorCodes.THIS_USER_DOES_NOT_EXIST", HttpStatus.BAD_REQUEST),
    ACCESS_DENIED(101, "ErrorCodes.ACCESS_DENIED", HttpStatus.FORBIDDEN),
    DATA_NOT_FOUND(100, "ErrorCodes.ACCESS_DENIED", HttpStatus.BAD_REQUEST),
    THIS_USERNAME_HAS_TAKEN_BEFORE(100,"ErrorCodes.THIS_USERNAME_HAS_TAKEN_BEFORE",HttpStatus.BAD_REQUEST),
    PRODUCT_NAME_CAN_NOT_BE_EMPTY(100,"ErrorCodes.PRODUCT_NAME_CAN_NOT_BE_EMPTY" , HttpStatus.BAD_REQUEST),
    PRODUCT_PRICE_CAN_NOT_BE_EMPTY(100,"ErrorCodes.PRODUCT_PRICE_CAN_NOT_BE_EMPTY" , HttpStatus.BAD_REQUEST),
    PRODUCT_QUANTITY_IS_NOT_ENOUGH(100,"ErrorCodes.PRODUCT_QUANTITY_IS_NOT_ENOUGH" , HttpStatus.BAD_REQUEST),
    ORDER_DOES_NOT_EXIST(100,"ErrorCodes.ORDER_DOES_NOT_EXIST" , HttpStatus.BAD_REQUEST),
            ;

    private ErrorCodes(Integer code, String langKey, HttpStatus httpStatus) {
        this.code = code;
        this.httpStatus = httpStatus;
        this.langKey = langKey;
    }

    @Getter
    private Integer code;

    @Getter
    private String langKey;

    @Getter
    private HttpStatus httpStatus;

    /**
     * @param code
     * @return
     */
    public ErrorCodes findByCode(Integer code) {
        return Arrays.asList(ErrorCodes.values()).stream().filter(f -> f.getCode().equals(code)).findFirst().orElse(ErrorCodes.SYSTEM_FAILURE);
    }

    @Override
    public String getName() {
        return this.name();
    }

    @Override
    public String langKey() {
        return this.langKey;
    }
    }
