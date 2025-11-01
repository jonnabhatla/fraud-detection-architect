package com.fraud.transaction.api;

import jakarta.validation.constraints.NotBlank;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Map;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

  private final KafkaTemplate<String, String> kafkaTemplate;

  public TransactionController(KafkaTemplate<String, String> kafkaTemplate) {
    this.kafkaTemplate = kafkaTemplate;
  }

  @PostMapping
  public Map<String, Object> submit(@RequestBody Map<String, Object> txn) {
    String id = String.valueOf(txn.getOrDefault("txnId", "TXN-" + Instant.now().toEpochMilli()));
    kafkaTemplate.send("txn-events", id, txn.toString());
    return Map.of("status","PUBLISHED","txnId", id, "publishedTo","txn-events");
  }

  @GetMapping("/health")
  public Map<String,String> health() { return Map.of("status","UP"); }
}
