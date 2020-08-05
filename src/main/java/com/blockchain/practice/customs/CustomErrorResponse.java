package com.blockchain.practice.customs;

@SuppressWarnings("serial")
public class CustomErrorResponse extends RuntimeException {
 private Integer code;

 public CustomErrorResponse(Integer code, String message) {
  super(message);
  this.code = code;
 }

 public Integer getCode() {
  return code;
 }
}