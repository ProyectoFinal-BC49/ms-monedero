package com.nttdatabc.msmonedero.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import reactor.core.publisher.Mono;
import reactor.core.publisher.MonoSink;

@Configuration
public class KafkaConsumerListener {
  private MonoSink<String> debCardVerificationResponseSink;
  private MonoSink<String> debCardVerificationBalanceResponseSink;
  @KafkaListener(topics = {"response-verify-carddeb-exist"}, groupId = "my-group-id")
  public void listenerResponseVerifyCardDeb(String message){
    if (debCardVerificationResponseSink != null) {
      debCardVerificationResponseSink.success(message);
      debCardVerificationResponseSink = null;
    }
  }
  public Mono<String> getDebCardVerificationResponseSink() {
    return Mono.create(sink -> debCardVerificationResponseSink = sink);
  }

  @KafkaListener(topics = {"response-verify-balance-carddeb"}, groupId = "my-group-id")
  public void listenerResponseVerifyBalanceCardDeb(String message){
    if (debCardVerificationBalanceResponseSink != null) {
      debCardVerificationBalanceResponseSink.success(message);
      debCardVerificationBalanceResponseSink = null;
    }
  }
  public Mono<String> getDebCardVerificationBalanceResponseSink() {
    return Mono.create(sink -> debCardVerificationBalanceResponseSink = sink);
  }
}
