package com.blockchain.practice.models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SuppressWarnings("serial")
public class Block implements java.io.Serializable {
 private UUID id;
 private Integer index;
 private String previousHash;
 private String hash;
 private Integer nonce;
 private Long timestamp;
 private List<Transaction> transactions = new ArrayList<>();

 public Block() {}

 public Block(UUID id, Integer index, String previousHash, String hash, Integer nonce, Long timestamp, Transaction transaction) {
  this.id = id;
  this.index = index;
  this.previousHash = previousHash;
  this.hash = hash;
  this.nonce = nonce;
  this.timestamp = timestamp;
  transactions.add(transaction);

  System.out.println(String.format("New transaction: %s", transaction.toString()));
 }

 public void setHash(String hash) {
  this.hash = hash;
 }

 public void setNonce(Integer nonce) {
  this.nonce = nonce;
 }

 public UUID getId() {
  return id;
 }

 public Integer getIndex() {
  return index;
 }

 public String getPreviousHash() {
  return previousHash;
 }

 public String getHash() {
  return hash;
 }

 public Integer getNonce() {
  return nonce;
 }

 public Long getTimestamp() {
  return timestamp;
 }

 public List<Transaction> getTransactions() {
  return transactions;
 }
}