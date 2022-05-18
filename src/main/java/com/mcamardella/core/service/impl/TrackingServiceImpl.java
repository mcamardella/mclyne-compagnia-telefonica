package com.mcamardella.core.service.impl;

import com.mcamardella.core.entity.ClientEntity;
import com.mcamardella.core.entity.TrackingEntity;
import com.mcamardella.core.enumeration.MessageStatusEnum;
import com.mcamardella.core.message.MessageStatus;
import com.mcamardella.core.repository.ClientRepository;
import com.mcamardella.core.repository.TrackingRepository;
import com.mcamardella.core.service.TrackingService;
import com.mcamardella.core.utility.Utility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class TrackingServiceImpl implements TrackingService {
    private static final Logger logger = LoggerFactory.getLogger(TrackingServiceImpl.class);
    @Autowired
    TrackingRepository trackingRepository;

    @Autowired
    ClientRepository clientRepository;

    private static final String REASON = "API PER LA GESTIONE E MANIPOLAZIONE DEL TRACKING DELLE CHIAMATE";

    @Override
    public MessageStatus getAllTracking() {
        logger.info("ESECUZIONE SERVICE PER IL GET ALL TRACKINGS");
        return new MessageStatus(
                "TRACKING TROVATI",
                "TRACKING PRESENTI NEL DATABASE",
                REASON,
                MessageStatusEnum.OK,
                HttpStatus.OK.value(),
                Map.of("trackings", trackingRepository.findAll())
        );
    }

    @Override
    public MessageStatus create(Integer idClient, TrackingEntity trackingEntity) {
        logger.info("ESECUZIONE SERVICE PER IL CREATE TRACKING");
        ClientEntity clientEntity = clientRepository.findById(idClient).orElse(null);
        if (clientEntity == null) {
            logger.warn("CLIENT CON ID: " + idClient +" NON ESISTE NEL DATABASE");
            return new MessageStatus(
                    "CLIENT NON RECUPERATO",
                    "CLIENT CON ID: " + idClient +" NON ESISTE NEL DATABASE",
                    REASON,
                    MessageStatusEnum.KO,
                    HttpStatus.OK.value(),
                    null
            );
        }
        trackingEntity.setDurationToString(Utility.durationToSring(trackingEntity.getDateCallStart(), trackingEntity.getDateCallEnd()));
        trackingEntity.setDurationSeconds(Utility.durationSeconds(trackingEntity.getDateCallStart(), trackingEntity.getDateCallEnd()));
        trackingEntity.setClientEntity(clientEntity);
        return new MessageStatus(
                "TRACKING CREATO",
                "TRACKING CREATO CORRETTAMENTE",
                REASON,
                MessageStatusEnum.OK,
                HttpStatus.OK.value(),
                Map.of("tracking", trackingRepository.save(trackingEntity))
        );
    }
}
