package com.blockchain.practice.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.blockchain.practice.models.Block;
import com.blockchain.practice.models.Transaction;

public class BlockChainCreator {
 private List<Block> blocks = new ArrayList<>();

 public BlockChainCreator() {
  blocks.add(0, this.genesisBlock());
 }

 private Block genesisBlock() {
  BlockCreator creator = new BlockCreator(
   UUID.randomUUID(), 1, "00000", 0, new Transaction(null, null, 0)
  );
  return creator.getBlock();
 }
}