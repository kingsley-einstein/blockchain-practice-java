package com.blockchain.practice.utils;

import java.security.MessageDigest;

public class StringUtils {
 public static String createHash(String input) throws Exception {
  try {
   MessageDigest digest = MessageDigest.getInstance("SHA-256");
   byte[] bytes = digest.digest(input.getBytes("UTF-8"));
   StringBuffer buffer = new StringBuffer();
   for (byte b: bytes) {
    buffer.append(0xFF & b);
   }
   return buffer.toString();
  } catch (Exception ex) {
   throw new Exception(ex.getMessage());
  }
 }
}