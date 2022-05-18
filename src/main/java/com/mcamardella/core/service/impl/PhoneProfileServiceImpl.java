package com.mcamardella.core.service.impl;

import com.mcamardella.core.enumeration.MessageStatusEnum;
import com.mcamardella.core.entity.PhoneProfileEntity;
import com.mcamardella.core.enumeration.StatusPhoneLineEnum;
import com.mcamardella.core.message.MessageStatus;
import com.mcamardella.core.repository.PhoneProfileRepository;
import com.mcamardella.core.service.PhoneProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

@Service
public class PhoneProfileServiceImpl implements PhoneProfileService {
    private static final Logger logger = LoggerFactory.getLogger(PhoneProfileServiceImpl.class);
    @Autowired
    PhoneProfileRepository phoneProfileRepository;

    private static final String REASON = "API PER LA GESTIONE E MANIPOLAZIONE DELL'ENTITY PHONE PROFILE";

    @Override
    public MessageStatus getAllPhoneProfile() {
        logger.info("ESECUZIONE SERVICE PER IL GET ALL PHONE PROFILES");
        return new MessageStatus(
                "PHONE PROFILE TROVATI",
                "PHONE PROFILE TROVATI NEL DATABASE",
                REASON,
                MessageStatusEnum.OK,
                HttpStatus.OK.value(),
                Map.of("phoneprofiles", phoneProfileRepository.findAll())
        );
    }

    @Override
    public  MessageStatus create(PhoneProfileEntity phoneProfileEntity) {
        logger.info("ESECUZIONE SERVICE PER IL CREATE PHONE PROFILE");
        return new MessageStatus(
                "PHONE PROFILE CREATO",
                "PHONE PROFILE CREATO CORRETTAMENTE",
                REASON,
                MessageStatusEnum.OK,
                HttpStatus.OK.value(),
                Map.of("phoneprofile", phoneProfileRepository.save(phoneProfileEntity))
        );
    }

    @Override
    public MessageStatus update(Integer idPhoneProfile, PhoneProfileEntity phoneProfileEntity) {
        logger.info("ESECUZIONE SERVICE PER L'UPDATE PHONE PROFILE");
        Optional<PhoneProfileEntity> phoneProfile = phoneProfileRepository.findById(idPhoneProfile);
        if (phoneProfile.isPresent()) {
            PhoneProfileEntity phoneProfileUpdate = phoneProfile.get();
            phoneProfileUpdate.setPrefix(phoneProfileEntity.getPrefix());
            phoneProfileUpdate.setNumberPhone(phoneProfileEntity.getNumberPhone());
            phoneProfileUpdate.setTypologyLine(phoneProfileEntity.getTypologyLine());
            phoneProfileUpdate.setStatusPhoneLine(phoneProfileEntity.getStatusPhoneLine());
            phoneProfileUpdate.setUpdated(LocalDateTime.now());
            phoneProfileRepository.save(phoneProfileUpdate);
            return new MessageStatus(
                    "PHONE PROFILE AGGIORNATO",
                    "PHONE PROFILE AGGIORNATO CON SUCCESSO",
                    REASON,
                    MessageStatusEnum.OK,
                    HttpStatus.OK.value()
            );
        } else {
            logger.warn("NESSUN PHONE PROFILE TROVATO CON ID: " + idPhoneProfile);
            return new MessageStatus(
                    "PHONE PROFILE NON AGGIORNATO",
                    "PHONE PROFILE CON ID: " + idPhoneProfile + " NON PRESENTE NEL DATABASE",
                    REASON,
                    MessageStatusEnum.KO,
                    HttpStatus.NOT_FOUND.value()
            );
        }
    }

    @Override
    public MessageStatus delete(Integer idPhoneProfile) {
        logger.info("ESECUZIONE SERVICE PER IL DELETE PHONE PROFILE");
        Optional<PhoneProfileEntity> phoneProfile = phoneProfileRepository.findById(idPhoneProfile);
        if (phoneProfile.isPresent()) {
            phoneProfileRepository.deleteById(idPhoneProfile);
            return new MessageStatus(
                    "PHONE PROFILE DELETED",
                    "PHONE PROFILE DELETED WITH SUCCESS",
                    REASON,
                    MessageStatusEnum.OK,
                    HttpStatus.OK.value()
            );
        } else {
            logger.warn("NESSUN PHONE PROFILE TROVATO CON ID: " + idPhoneProfile);
            return new MessageStatus(
                    "PHONE PROFILE NON ELIMINATO",
                    "PHONE PROFILE CON ID: " + idPhoneProfile + " NON PRESENTE NEL DATABASE",
                    REASON,
                    MessageStatusEnum.KO,
                    HttpStatus.NOT_FOUND.value()
            );
        }
    }

    @Override
    public MessageStatus changeStatus(Integer idPhoneProfile, String status) {
        PhoneProfileEntity phoneProfile = phoneProfileRepository.findById(idPhoneProfile).orElse(null);
        if (phoneProfile != null) {
            try {
                phoneProfile.setStatusPhoneLine(StatusPhoneLineEnum.valueOf(status.toUpperCase()));
            } catch (Exception exception) {
                exception.printStackTrace();
                logger.warn("STATUS: " + status + "NON VALIDO");
                return new MessageStatus(
                        "PHONE PROFILE NON AGGIORNATO",
                        "STATUS: " + status.toUpperCase() + " NON VALIDO",
                        REASON,
                        MessageStatusEnum.OK,
                        HttpStatus.OK.value(),
                        null
                );
            }

            phoneProfileRepository.save(phoneProfile);
            return new MessageStatus(
                    "STSTUS PHONE PROFILE AGGIORNATO",
                    "CAMBIATO STATO IN: " + status.toUpperCase() + " CON SUCCESO",
                    REASON,
                    MessageStatusEnum.OK,
                    HttpStatus.OK.value(),
                    null
            );
        } else {
            logger.warn("NESSUN PHONE PROFILE TROVATO CON ID: " + idPhoneProfile);
            return new MessageStatus(
                    "STATUS PHONE PROFILE NON AGGIORNATO",
                    "PHONE PROFILE CON ID: " + idPhoneProfile + " NON PRESENTE NEL DATABASE",
                    REASON,
                    MessageStatusEnum.OK,
                    HttpStatus.OK.value(),
                    null
            );
        }
    }
}
