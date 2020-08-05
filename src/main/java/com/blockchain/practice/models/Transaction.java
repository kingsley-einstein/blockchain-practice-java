package com.blockchain.practice.models;

import java.util.UUID;

@SuppressWarnings("serial")
public class Transaction implements java.io.Serializable {
 UUID sender;
 UUID recipient;
 Integer amount;
 
 public Transaction() {}

 public Transaction(UUID sender, UUID recipient, Integer amount) {
  this.sender = sender;
  this.recipient = recipient;
  this.amount = amount;
 }

 public UUID getSender() {
  return sender;
 }

 public UUID getRecipient() {
  return recipient;
 }

 public Integer getAmount() {
  return amount;
 }

 public String toString() {
  return String.format(
   "{\n sender: %s, \n recipient: %s, \n amount: %d \n}",
   this.getSender().toString(),
   this.getRecipient().toString(),
   this.getAmount()
  );
 }
}