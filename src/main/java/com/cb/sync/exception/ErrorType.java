package com.cb.sync.exception;

/**
 * Api Error Type Enum
 *
 * @author Balaji
 */
public enum ErrorType {
  CUSTOMER_NOT_FOUND(1, "Customer not found"),
  CUSTOMER_ALREADY_EXISTS(2, "Customer already exists"),
  PRODUCT_NOT_FOUND(3, "Product not found"),
  USER_NOT_FOUND(4, "User not found"),
  USER_ALREADY_EXISTS(5, "User already exists"),
  UPLOAD_FAILED(6, "Cannot upload helpdesk info");

  private final int code;
  private final String message;

  ErrorType(int code, String message) {
    this.code = code;
    this.message = message;
  }

  public int getCode() {
    return code;
  }

  public String getMessage() {
    return message;
  }
}
