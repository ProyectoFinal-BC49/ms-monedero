package com.nttdatabc.msmonedero.services;

import com.nttdatabc.msmonedero.config.KafkaConsumerListener;
import com.nttdatabc.msmonedero.model.Wallet;
import com.nttdatabc.msmonedero.repository.WalletRepository;
import com.nttdatabc.msmonedero.service.WalletServiceImpl;
import com.nttdatabc.msmonedero.service.strategy.validation_wallet.ContextValidation;
import com.nttdatabc.msmonedero.service.strategy.validation_wallet.ValidateDocument;
import com.nttdatabc.msmonedero.utils.Utilitarios;
import com.nttdatabc.msmonedero.utils.exceptions.errors.ErrorResponseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ReactiveListOperations;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;


@SpringBootTest
public class WalletServiceTest {
  @Mock
  private WalletRepository walletRepository;
  @Mock
  private KafkaTemplate<String, Wallet> kafkaTemplate;

  @Mock
  private KafkaConsumerListener kafkaConsumerListener;
  @InjectMocks
  private WalletServiceImpl walletService;

  @BeforeEach
  public void setup(){
    MockitoAnnotations.openMocks(this);
  }
  @Test
  public void getWalletByIdService_Found(){
    //Arrange
    Wallet walletFound = new Wallet();
    walletFound.setId("22333");
    when(walletRepository.findById("22333")).thenReturn(Mono.just(walletFound));

    //Act
    Mono<Wallet>walletMonoFound = walletService.getWalletByIdService("22333");

    //Assert
    StepVerifier.create(walletMonoFound)
        .expectNext(walletFound)
        .verifyComplete();
    verify(walletRepository,times(1)).findById("22333");

  }
  @Test
  public void getWalletByIdService_(){
    //Arrange
    when(walletRepository.findById("22333")).thenReturn(Mono.empty());

    //Act
    Mono<Wallet>walletMonoFound = walletService.getWalletByIdService("22333");

    //Assert
    StepVerifier.create(walletMonoFound)
        .expectError(ErrorResponseException.class);

    verify(walletRepository,times(1)).findById("22333");

  }

  @Test
  public void delete_Found(){
    //Arrange
    Wallet walletFound = new Wallet();
    walletFound.setId("22333");
    when(walletRepository.findById("22333")).thenReturn(Mono.just(walletFound));
    when(walletRepository.delete(walletFound)).thenReturn(Mono.empty());

    //Act
    Mono<Void> delete = walletService.deleteWalletService("22333");
    StepVerifier.create(delete)
        .verifyComplete();

    //Assert
    verify(walletRepository,times(1)).delete(walletFound);
  }
  @Test
  public void delete_Not_Found(){
    //Arrange
    Wallet walletFound = new Wallet();
    walletFound.setId("22333");
    when(walletRepository.findById("22333")).thenReturn(Mono.empty());
    when(walletRepository.delete(walletFound)).thenReturn(Mono.empty());

    //Act
    Mono<Void> delete = walletService.deleteWalletService("22333");
    StepVerifier.create(delete)
        .expectError(ErrorResponseException.class);

    //Assert
    verify(walletRepository,never()).delete(walletFound);
  }

}
