package com.nttdatabc.msmonedero.service;

import static com.nttdatabc.msmonedero.utils.MovementValidation.validateCentralFacade;

import com.nttdatabc.msmonedero.config.KafkaConsumerListener;
import com.nttdatabc.msmonedero.model.MovementWallet;
import com.nttdatabc.msmonedero.repository.MovementWalletRepository;
import com.nttdatabc.msmonedero.repository.WalletRepository;
import com.nttdatabc.msmonedero.service.interfaces.MovementWalletService;
import com.nttdatabc.msmonedero.service.interfaces.WalletService;
import com.nttdatabc.msmonedero.service.strategy.insert_movement.ContextInsert;
import com.nttdatabc.msmonedero.service.strategy.insert_movement.InsertDebitCard;
import com.nttdatabc.msmonedero.service.strategy.insert_movement.InsertDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


/**
 * Service Wallet.
 */
@Service
public class MovementWalletServiceImpl implements MovementWalletService {
  @Autowired
  private MovementWalletRepository movementWalletRepository;
  @Autowired
  private WalletRepository walletRepository;
  @Autowired
  private WalletService walletService;
  @Autowired
  private KafkaConsumerListener kafkaConsumerListener;

  @Autowired
  private KafkaTemplate<String, String> kafkaTemplate;

  @Override
  public Mono<Void> createMovementWalletservice(MovementWallet movementWallet) {
    return validateCentralFacade(movementWallet, walletRepository)
        .then(walletService.getWalletByIdService(movementWallet.getWalletId()))
        .flatMap(walletFlujo -> {
          ContextInsert ctxInsert = null;
          if (walletFlujo.getCardDebitAssociate() == null) {
            ctxInsert = new ContextInsert(new InsertDocument());
          } else {
            ctxInsert = new ContextInsert(new InsertDebitCard());
          }
          return ctxInsert.executeInsert(walletFlujo, movementWallet, movementWalletRepository,
              walletRepository, kafkaTemplate, kafkaConsumerListener);
        });
  }
}
