package com.blockchain.practice.service;

import java.util.List;
import java.util.UUID;

import com.blockchain.practice.customs.CustomErrorResponse;
import com.blockchain.practice.models.Block;
import com.blockchain.practice.models.Transaction;
import com.blockchain.practice.utils.BlockChainCreator;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Component;

public class BlockChainService {
 @Autowired
 private BlockChainCreator creator;

 public BlockChainService() {}

 public Block addBlock(Transaction t) {
  try {
   Block b = creator.addBlock(t);
   Boolean chainValid = creator.chainIsValid();
   if (!chainValid) {
    throw new CustomErrorResponse(400, "Chain is no longer valid. A block has been tampered with.");
   }
   return b;
  } catch (CustomErrorResponse error) {
   throw new CustomErrorResponse(error.getCode(), error.getMessage());
  } catch (Exception ex) {
   throw new CustomErrorResponse(500, ex.getMessage());
  }
 }

 public List<Block> getBlocks() {
  try {
   return creator.getBlocks();
  } catch (Exception ex) {
   throw new CustomErrorResponse(500, ex.getMessage());
  }
 }

 public Block findBlockById(UUID id) {
  try {
   Block b = creator.findBlockById(id);
   if (b == null) throw new CustomErrorResponse(404, "Block not found");
   return b;
  } catch (CustomErrorResponse error) {
   throw new CustomErrorResponse(error.getCode(), error.getMessage());
  } catch (Exception ex) {
   throw new CustomErrorResponse(500, ex.getMessage());
  }
 }

 public Boolean isChainValid() {
  try {
   return creator.chainIsValid();
  } catch (CustomErrorResponse error) {
   throw new CustomErrorResponse(error.getCode(), error.getMessage());
  }
 }
}