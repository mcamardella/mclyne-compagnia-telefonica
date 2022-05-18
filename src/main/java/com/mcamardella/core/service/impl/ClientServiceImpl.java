package com.mcamardella.core.service.impl;

import com.mcamardella.core.controller.ClientController;
import com.mcamardella.core.entity.ClientEntity;
import com.mcamardella.core.enumeration.MessageStatusEnum;
import com.mcamardella.core.message.MessageStatus;
import com.mcamardella.core.repository.ClientRepository;
import com.mcamardella.core.service.ClientService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {
    private static final Logger logger = LoggerFactory.getLogger(ClientServiceImpl.class);
    @Autowired
    ClientRepository clientRepository;

    private static final String REASON = "API PER LA GESTIONE E MANIPOLAZIONE DELL'ENTITY CLIENT";

    @Override
    public MessageStatus getAllClients() {
        logger.info("ESECUZIONE SERVICE PER IL GET ALL CLIENTS");
        return new MessageStatus(
                "CLIENTI TROVATI",
                "CLIENTI PRESENTI NEL DATABASE",
                REASON,
                MessageStatusEnum.OK,
                HttpStatus.OK.value(),
                Map.of("clients", clientRepository.findAll())
        );
    }

    @Override
    public MessageStatus create(ClientEntity clientEntity) {
        logger.info("ESECUZIONE SERVICE PER IL CREATE CLIENT");
        clientEntity.setCreated(LocalDateTime.now());
        return new MessageStatus(
                "CLIENTI CREATO",
                "CLIENTI CREATO CORRETTAMENTE",
                REASON,
                MessageStatusEnum.OK,
                HttpStatus.OK.value(),
                Map.of("client", clientRepository.save(clientEntity))
        );
    }

    @Override
    public MessageStatus update(Integer idClient, ClientEntity clientEntity) {
        logger.info("ESECUZIONE SERVICE PER L'UPDATE CLIENT");
        Optional<ClientEntity> client = clientRepository.findById(idClient);
        if (client.isPresent()) {
            ClientEntity clientUpdate = client.get();
            clientUpdate.setFirstName(clientEntity.getFirstName());
            clientUpdate.setLastName(clientEntity.getLastName());
            clientUpdate.setDateOfBirth(clientEntity.getDateOfBirth());
            clientUpdate.setGender(clientEntity.getGender());
            clientUpdate.setAddress(clientEntity.getAddress());
            clientUpdate.setCity(clientEntity.getCity());
            clientUpdate.setProvince(clientEntity.getProvince());
            clientUpdate.setPostCode(clientEntity.getPostCode());
            clientUpdate.setCountry(clientEntity.getCountry());
            clientUpdate.setEmail(clientEntity.getEmail());
            clientUpdate.setActive(clientEntity.getActive());
            clientUpdate.setCreated(clientEntity.getCreated());
            clientUpdate.setUpdated(LocalDateTime.now());
            clientRepository.save(clientUpdate);
            return new MessageStatus(
                    "CLIENTE AGGIORNATO",
                    "CLIENTE AGGIORNATO CON SUCCESSO",
                    REASON,
                    MessageStatusEnum.OK,
                    HttpStatus.OK.value()
            );
        } else {
            logger.warn("NESSUN CLIENT TROVATO CON ID: " + idClient);
            return new MessageStatus(
                    "CLIENTE NON AGGIORNATO",
                    "CLIENTE CON ID: " + idClient + " NON PRESENTE NEL DATABASE",
                    REASON,
                    MessageStatusEnum.KO,
                    HttpStatus.NOT_FOUND.value()
            );
        }
    }

    @Override
    public MessageStatus delete(Integer idClient) {
        logger.info("ESECUZIONE SERVICE PER IL DELETE CLIENT");
        Optional<ClientEntity> client = clientRepository.findById(idClient);
        if (client.isPresent()) {
            clientRepository.deleteById(idClient);
            return new MessageStatus(
                    "CLIENTE ELIMINATO",
                    "CLIENTE ELIMINATO CON SUCCESSO",
                    REASON,
                    MessageStatusEnum.OK,
                    HttpStatus.OK.value()
            );
        } else {
            logger.warn("NESSUN CLIENT TROVATO CON ID: " + idClient);
            return new MessageStatus(
                    "CLIENTE NON ELIMINATO",
                    "CLIENTE CON ID: " + idClient + " NON PRESENTE NEL DATABASE",
                    REASON,
                    MessageStatusEnum.KO,
                    HttpStatus.NOT_FOUND.value()
            );
        }
    }

    @Override
    public MessageStatus getClientById(Integer idClient) {
        logger.info("ESECUZIONE SERVICE PER IL GET CLIENT BY ID");
        ClientEntity clientEntity = clientRepository.findById(idClient).orElse(null);
        if (clientEntity != null) {
            return new MessageStatus(
                    "CLIENTE TROVATO",
                    "CLIENTE TROVATO CON SUCCESSO",
                    REASON,
                    MessageStatusEnum.OK,
                    HttpStatus.OK.value(),
                    Map.of("client", clientEntity)
            );
        } else {
            logger.warn("NESSUN UTENTE TROVATO CON ID: " + idClient);
            return new MessageStatus(
                    "CLIENTE NON TROVATO",
                    "CLIENTE CON ID: " + idClient + " NON PRESENTE NEL DATABASE",
                    REASON,
                    MessageStatusEnum.KO,
                    HttpStatus.NOT_FOUND.value()
            );
        }
    }

    @Override
    public MessageStatus getClientByEmail(String email) {
        logger.info("ESECUZIONE SERVICE PER IL GET CLIENT BY EMAIL");
        ClientEntity clientEntity = clientRepository.getClientEntitiesByEmail(email);
        if (clientEntity != null) {
            return new MessageStatus(
                    "CLIENTE TROVATO",
                    "CLIENTE TROVATO CON SUCCESSO",
                    REASON,
                    MessageStatusEnum.OK,
                    HttpStatus.OK.value(),
                    Map.of("client", clientEntity)
            );
        } else {
            logger.warn("NESSUN CLIENT TROVATO CON EMAIL: " + email);
            return new MessageStatus(
                    "CLIENTE NON TROVATO",
                    "CLIENTE CON EMAIL: " + email + " NON PRESENTE NEL DATABASE",
                    REASON,
                    MessageStatusEnum.KO,
                    HttpStatus.NOT_FOUND.value()
            );
        }
    }
}
