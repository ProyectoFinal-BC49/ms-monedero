package com.nttdatabc.msmonedero.config;

import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

/**
 * Configuración de Kafka, para la creación de topics.
 */
@Configuration
public class KafkaTopicConfig {
  @Bean
  public NewTopic generateTopicWallet() {
    Map<String, String> configuration = new HashMap<>();
    configuration.put(TopicConfig.CLEANUP_POLICY_CONFIG, TopicConfig.CLEANUP_POLICY_DELETE);
    configuration.put(TopicConfig.RETENTION_MS_CONFIG, "86400000");
    configuration.put(TopicConfig.SEGMENT_BYTES_CONFIG, "1073741824");
    configuration.put(TopicConfig.MAX_MESSAGE_BYTES_CONFIG, "1000012");
    return TopicBuilder.name("verify-carddeb-exist")
        .configs(configuration)
        .build();
  }

  @Bean
  public NewTopic generateTopicUpdateMountCardDeb() {
    Map<String, String> configuration = new HashMap<>();
    configuration.put(TopicConfig.CLEANUP_POLICY_CONFIG, TopicConfig.CLEANUP_POLICY_DELETE);
    configuration.put(TopicConfig.RETENTION_MS_CONFIG, "86400000");
    configuration.put(TopicConfig.SEGMENT_BYTES_CONFIG, "1073741824");
    configuration.put(TopicConfig.MAX_MESSAGE_BYTES_CONFIG, "1000012");
    return TopicBuilder.name("update-amount-carddeb")
        .configs(configuration)
        .build();
  }

  @Bean
  public NewTopic generateTopicVerifyBalanceCardDeb() {
    Map<String, String> configuration = new HashMap<>();
    configuration.put(TopicConfig.CLEANUP_POLICY_CONFIG, TopicConfig.CLEANUP_POLICY_DELETE);
    configuration.put(TopicConfig.RETENTION_MS_CONFIG, "86400000");
    configuration.put(TopicConfig.SEGMENT_BYTES_CONFIG, "1073741824");
    configuration.put(TopicConfig.MAX_MESSAGE_BYTES_CONFIG, "1000012");
    return TopicBuilder.name("verify-balance-carddeb")
        .configs(configuration)
        .build();
  }
}
