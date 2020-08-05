package com.blockchain.practice.config;

import com.blockchain.practice.service.BlockChainService;
import com.blockchain.practice.utils.BlockChainCreator;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
 @Bean
 public BlockChainCreator chainCreator() {
  return new BlockChainCreator();
 }

 @Bean
 public BlockChainService chainService() {
  return new BlockChainService();
 }
}