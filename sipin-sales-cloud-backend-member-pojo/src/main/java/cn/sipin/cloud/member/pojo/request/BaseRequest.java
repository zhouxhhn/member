/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package cn.sipin.cloud.member.pojo.request;

import org.springframework.validation.BindingResult;

/**
 * 基类Request
 */
public abstract class BaseRequest {

  public abstract BindingResult appendValidation(BindingResult errors);

  public String joinErrorMessage(BindingResult errors) {
    StringBuilder errorMessage = new StringBuilder();
    errors.getAllErrors().forEach(
        error ->
          errorMessage.append(error.getDefaultMessage()).append(";")
    );

    return errorMessage.toString();
  }
}
