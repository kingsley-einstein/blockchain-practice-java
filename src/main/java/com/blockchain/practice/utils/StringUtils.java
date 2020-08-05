package com.blockchain.practice.utils;

import java.security.MessageDigest;

import com.blockchain.practice.customs.CustomErrorResponse;

public class StringUtils {
 public static String createHash(String input) {
  try {
   MessageDigest digest = MessageDigest.getInstance("SHA-256");
   byte[] bytes = digest.digest(input.getBytes("UTF-8"));
   StringBuffer buffer = new StringBuffer();
   for (byte b: bytes) {
    buffer.append(0xFF & b);
   }
   return buffer.toString();
  } catch (Exception ex) {
   throw new CustomErrorResponse(500, ex.getMessage());
  }
 }
}