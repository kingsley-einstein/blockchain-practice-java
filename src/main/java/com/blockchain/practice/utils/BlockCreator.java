package com.blockchain.practice.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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
     id.toString() + Integer.toString(index) + previousHash + Integer.toString(nonce) + transactions.get(0).toString()
    ), 
    nonce, 
    timestamp, 
    transaction
   );
   this.block = this.mineBlock(this.block, 5);
  } catch (Exception ex) {
   ex.printStackTrace();
  }
 }

 private String createHash(String input) throws Exception {
  return StringUtils.createHash(input);
 }

 private Block mineBlock(Block b, Integer difficulty) throws Exception {
  String target = new String(new char[difficulty + 1]).replace("\0", "0");
  while(!b.getHash().substring(0, difficulty).equals(target)) {
   b.setNonce(b.getNonce() + 1);
   b.setHash(this.createHash(
    b.getId().toString() + Integer.toString(b.getIndex()) + b.getPreviousHash() + Integer.toString(b.getNonce()) + b.getTransactions().get(0).toString()
   ));
  }
  return b;
 }

 public Block getBlock() {
  return block;
 }
}