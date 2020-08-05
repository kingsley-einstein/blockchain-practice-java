package com.blockchain.practice.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.blockchain.practice.customs.CustomErrorResponse;
import com.blockchain.practice.models.Block;
import com.blockchain.practice.models.Transaction;

// import org.springframework.stereotype.Component;

public class BlockChainCreator {
 private List<Block> blocks = new ArrayList<>();

 public BlockChainCreator() {
  blocks.add(0, this.genesisBlock());
 }

 private Block genesisBlock() {
  BlockCreator creator = new BlockCreator(
   UUID.randomUUID(), 1, "00000", 0, new Transaction(UUID.randomUUID(), UUID.randomUUID(), 0)
  );
  return creator.getBlock();
 }

 public Block addBlock(Transaction transaction) {
  Block lastBlock = blocks.get(blocks.size() - 1);
  Integer index = lastBlock.getIndex() + 1;
  String previousHash = lastBlock.getHash();
  BlockCreator creator = new BlockCreator(
   UUID.randomUUID(), index, previousHash, 0, transaction
  );
  creator.mineBlock(creator.getBlock(), 3);
  blocks.add(creator.getBlock());
  return creator.getBlock();
 }

 private Boolean hashIsPure(Block b) {
  try {
   String encrypt = StringUtils.createHash(
    b.getId().toString() + Integer.toString(b.getIndex()) + b.getPreviousHash() + Integer.toString(b.getNonce()) + b.getTransactions().toString()
   );
   return b.getHash().equals(encrypt);
  } catch (Exception ex) {
   throw new CustomErrorResponse(500, ex.getMessage());
  }
 }

 public Boolean chainIsValid() {
  for (int i = 1; i < blocks.size(); i++) {
   Block currentBlock = blocks.get(i);
   Block previousBlock = blocks.get(i - 1);
   if (!this.hashIsPure(currentBlock)) return false;
   if (!currentBlock.getPreviousHash().equals(previousBlock.getHash())) return false;
  }
  return true;
 }

 public List<Block> getBlocks() {
  return blocks;
 }

 public Block findBlockById(UUID id) {
  Block block = null;
  for (Block b: blocks) {
   if (b.getId().equals(id)) block = b;
  }
  return block;
 }
}