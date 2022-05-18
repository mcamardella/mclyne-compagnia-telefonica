package com.mcamardella.core.service.impl;

import com.mcamardella.core.entity.ClientEntity;
import com.mcamardella.core.enumeration.MessageStatusEnum;
import com.mcamardella.core.entity.PhoneProfileEntity;
import com.mcamardella.core.message.MessageStatus;
import com.mcamardella.core.repository.ClientRepository;
import com.mcamardella.core.repository.PhoneProfileRepository;
import com.mcamardella.core.service.AssignmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class AssignmentServiceImpl implements AssignmentService {
    private static final Logger logger = LoggerFactory.getLogger(AssignmentServiceImpl.class);
    @Autowired
    ClientRepository clientRepository;

    @Autowired
    PhoneProfileRepository phoneProfileRepository;

    private static final String REASON = "API PER LA GESTIONE DELLA RELAZIONE/ASSEGNAZIONE CLIENT-PHONE PROFILE";

    @Override
    public MessageStatus assignPhoneProfile(Integer idClient, Integer idPhoneProfile) {
        logger.info("ESECUZIONE SERVICE PER L'ASSOCIZIONE PROFILE PHONE AL CLIENT");
        ClientEntity clientEntity = clientRepository.findById(idClient).orElse(null);
        PhoneProfileEntity phoneProfileEntity = phoneProfileRepository.findById(idPhoneProfile).orElse(null);
        if (clientEntity == null) {
            logger.warn("NESSUN CLIENT TROVATO CON ID: " + idClient);
            return new MessageStatus(
                    "CLIENT NON TROVATO",
                    "CLIENT CON ID: " + idClient + " NON PRESENTE NEL DATABASE",
                    REASON,
                    MessageStatusEnum.KO,
                    HttpStatus.NOT_FOUND.value()

            );
        }

        if (phoneProfileEntity == null) {
            logger.warn("NESSUN PHONE PROFILE TROVATO CON ID: " + idPhoneProfile);
            return new MessageStatus(
                    "PHONE PROFILE NON TROVATO",
                    "PHONE PROFILE CON ID: " + idPhoneProfile + " NON PRESENTE NEL DATABASE",
                    REASON,
                    MessageStatusEnum.KO,
                    HttpStatus.NOT_FOUND.value()
                    );
        }

        clientEntity.setPhoneProfile(phoneProfileEntity);
        clientEntity.setUpdated(LocalDateTime.now());
        phoneProfileEntity.setAssigned(LocalDateTime.now());
        clientRepository.save(clientEntity);
        return new MessageStatus(
                "CLIENT E PHONE PROFILE SONO STATI ASSOCIATI",
                "CLIENT E PHONE PROFILE SONO STATI ASSOCIATI CON SUCCESSO",
                REASON,
                MessageStatusEnum.OK,
                HttpStatus.OK.value()
        );
    }

    @Override
    public MessageStatus removePhoneProfile(Integer idClient, Integer idPhoneProfile) {
        ClientEntity clientEntity = clientRepository.findById(idClient).orElse(null);
        PhoneProfileEntity phoneProfileEntity = phoneProfileRepository.findById(idPhoneProfile).orElse(null);
        if (clientEntity == null) {
            return new MessageStatus(
                    "CLIENT NON TROVATO",
                    "CLIENT CON ID: " + idClient + " NON PRESENTE NEL DATABASE",
                    REASON,
                    MessageStatusEnum.KO,
                    HttpStatus.NOT_FOUND.value()

            );
        }

        if (phoneProfileEntity == null) {
            return new MessageStatus(
                    "PHONE PROFILE NON TROVATO",
                    "PHONE PROFILE CON ID: " + idPhoneProfile + " NON PRESENTE NEL DATABASE",
                    REASON,
                    MessageStatusEnum.KO,
                    HttpStatus.NOT_FOUND.value()
            );
        }

        clientEntity.setPhoneProfile(null);
        clientEntity.setUpdated(LocalDateTime.now());
        phoneProfileEntity.setAssigned(null);
        clientRepository.save(clientEntity);
        return new MessageStatus(
                "RIMOSSA ASSOCIAZIONE TRA CLIENT E PHONE PROFILE",
                "RIMOSSA ASSOCIAZIONE TRA CLIENT E PHONE PROFILE CON SUCCESSO",
                REASON,
                MessageStatusEnum.OK,
                HttpStatus.OK.value()
        );
    }
}
