package com.blockchain.practice.controller;

import java.util.List;
import java.util.UUID;

import com.blockchain.practice.customs.CustomErrorResponse;
import com.blockchain.practice.customs.CustomServerResponse;
import com.blockchain.practice.models.Block;
import com.blockchain.practice.models.Transaction;
import com.blockchain.practice.service.BlockChainService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class BlockChainController {
 @Autowired
 private BlockChainService service;

 @GetMapping
 public ResponseEntity<CustomServerResponse<String>> message() {
  return new ResponseEntity<>(
   new CustomServerResponse<String>(200, "Welcome to blockchain implementation with Java"),
   HttpStatus.OK
  );
 }

 @PostMapping("/create/block")
 public ResponseEntity<CustomServerResponse<Block>> createBlock(@RequestBody Transaction t) {
  try {
   Block block = service.addBlock(t);
   return new ResponseEntity<>(
    new CustomServerResponse<Block>(201, block),
    HttpStatus.CREATED
   );
  } catch (CustomErrorResponse error) {
   throw new CustomErrorResponse(error.getCode(), error.getMessage());
  }
 }

 @GetMapping("/find/{id}")
 public ResponseEntity<CustomServerResponse<Block>> getBlock(@PathVariable("id") UUID id) {
  try {
   Block block = service.findBlockById(id);
   return new ResponseEntity<>(
    new CustomServerResponse<Block>(200, block),
    HttpStatus.OK
   );
  } catch (CustomErrorResponse error) {
   throw new CustomErrorResponse(error.getCode(), error.getMessage());
  }
 }

 @GetMapping("/all")
 public ResponseEntity<CustomServerResponse<List<Block>>> getBlocks() {
  try {
   List<Block> blocks = service.getBlocks();
   return new ResponseEntity<>(
    new CustomServerResponse<List<Block>>(200, blocks),
    HttpStatus.OK
   );
  } catch (CustomErrorResponse error) {
   throw new CustomErrorResponse(error.getCode(), error.getMessage());
  }
 }
 
 @GetMapping("/confirm")
 public ResponseEntity<CustomServerResponse<String>> confirmChain() {
  try {
   Boolean isValid = service.isChainValid();
   String message = isValid ? "Chain is valid" : "Chain is invalid";
   return new ResponseEntity<>(
    new CustomServerResponse<String>(200, message),
    HttpStatus.OK
   );
  } catch (CustomErrorResponse error) {
   throw new CustomErrorResponse(error.getCode(), error.getMessage());
  }
 }
}