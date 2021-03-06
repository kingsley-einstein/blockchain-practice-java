package com.blockchain.practice.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.blockchain.practice.customs.CustomErrorResponse;
import com.blockchain.practice.models.Block;
import com.blockchain.practice.models.Transaction;

public class BlockCreator {
 private Block block;

 public BlockCreator(UUID id, Integer index, String previousHash, Integer nonce, Transaction transaction) {
  Long timestamp = new Date().getTime();
  List<Transaction> transactions = new ArrayList<>();
  transactions.add(transaction);
  try {
    this.block = new Block(
    id, 
    index, 
    previousHash,
    this.createHash(
     id.toString() + Integer.toString(index) + previousHash + Integer.toString(nonce) + transactions.toString()
    ), 
    nonce, 
    timestamp, 
    transaction
   );
   // this.block = this.mineBlock(block, 5);
  } catch (CustomErrorResponse error) {
   throw new CustomErrorResponse(error.getCode(), error.getMessage());
  }
 }

 private String createHash(String input) {
  return StringUtils.createHash(input);
 }

 public Block mineBlock(Block b, Integer difficulty) {
  String target = new String(new char[difficulty]).replace("\0", "0");
  while(!b.getHash().substring(0, difficulty).equals(target)) {
   b.setNonce(b.getNonce() + 1);
   b.setHash(this.createHash(
    b.getId().toString() + Integer.toString(b.getIndex()) + b.getPreviousHash() + Integer.toString(b.getNonce()) + b.getTransactions().toString()
   ));
  }
  return b;
 }

 public Block getBlock() {
  return block;
 }
}