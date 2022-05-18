package com.mcamardella.core.service.impl;

import com.mcamardella.core.entity.TrackingEntity;
import com.mcamardella.core.enumeration.MessageStatusEnum;
import com.mcamardella.core.message.MessageStatus;
import com.mcamardella.core.model.ReportModel;
import com.mcamardella.core.repository.ClientRepository;
import com.mcamardella.core.repository.PhoneProfileRepository;
import com.mcamardella.core.repository.TrackingRepository;
import com.mcamardella.core.service.ReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {
    private static final Logger logger = LoggerFactory.getLogger(ReportServiceImpl.class);

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    PhoneProfileRepository phoneProfileRepository;

    @Autowired
    TrackingRepository trackingRepository;

    private static final String REASON = "API PER LA REPORTISTICA";

    @Override
    public MessageStatus getTrackingByNumberPhone(String numberPhone) {
        logger.info("ESECUZIONE SERVICE PER IL GET TRACKING BY NUMBER PHONE");
        ReportModel reportModel = new ReportModel();
        List<TrackingEntity> trackingEntityList = trackingRepository.findByClientEntity_PhoneProfileNumberPhone(numberPhone);
        Long totalSecondsCall = Long.valueOf(0);
        Integer totalCalls = 0;
        if (trackingEntityList.isEmpty()) {
            logger.warn("NESSUN TRACKING RECUPERATO ASSOCIATO AL NUMERO: " + numberPhone);
            return new MessageStatus(
                    "NESSUN TRACKING RECUPERATO",
                    "NESSUN TRACKING RECUPERATO ASSOCIATO AL NUMERO: " + numberPhone,
                    REASON,
                    MessageStatusEnum.KO,
                    HttpStatus.NOT_FOUND.value(),
                    null

            );
        }

        for (TrackingEntity trackingEntity : trackingEntityList) {
            if (trackingEntity.getClientEntity().getPhoneProfile() == null) {
                logger.warn("NESSUN ROFILE PHONE ASSOCIATO AL NUMERO: " + numberPhone);
                return new MessageStatus(
                        "NESSUN TRACKING RECUPERATO",
                        "NESSUN PROFILE PHONE RECUPERATO ASSOCIATO AL NUMERO: " + numberPhone,
                        REASON,
                        MessageStatusEnum.KO,
                        HttpStatus.NOT_FOUND.value(),
                        null

                );
            }
            if (trackingEntity.getClientEntity().getPhoneProfile().getNumberPhone().equals(numberPhone)) {
                totalSecondsCall += trackingEntity.getDurationSeconds();
                totalCalls ++;
            } else {
                logger.warn("NESSUN PHONE PROFILE ASSOCIATO AL NUMERO: " + numberPhone);
                return new MessageStatus(
                        "TRACKING NON RECUPERATO",
                        "NESSUN PHONE PROFILE ASSOCIATO AL NUMERO: " + numberPhone,
                        REASON,
                        MessageStatusEnum.KO,
                        HttpStatus.NOT_FOUND.value(),
                        null

                );
            }
        }
        reportModel.setTotalSeconds(totalSecondsCall);
        reportModel.setCalls(totalCalls);
        reportModel.setTrackingEntity(trackingEntityList);
        return new MessageStatus(
                "TRACKING RECUPERATO",
                "TRACKING DEL NUMERO DI CELLULARE: " + numberPhone,
                REASON,
                MessageStatusEnum.KO,
                HttpStatus.NOT_FOUND.value(),
                Map.of("report", reportModel)

        );
    }

    @Override
    public MessageStatus getTrackingByNumberPhoneAndRangeTemporal(String numberPhone, LocalDateTime start, LocalDateTime end) {
        logger.info("ESECUZIONE SERVICE PER IL GET TRACKING BY NUMBER PHONE AND RANGE TEMPORAL");
        ReportModel reportModel = new ReportModel();
        List<TrackingEntity> trackingEntityList = trackingRepository.findTrackingEntitiesByDateCallStartIsBetween(start, end);
        Long totalSecondsCall = Long.valueOf(0);
        Integer totalCalls = 0;
        if (trackingEntityList.isEmpty()) {
            logger.warn("NESSUN TRACKING RECUPERATO ASSOCIATO AL NUMERO: " + numberPhone);
            return new MessageStatus(
                    "NESSUN TRACKING RECUPERATO",
                    "NESSUN TRACKING RECUPERATO ASSOCIATO AL NUMERO: " + numberPhone,
                    REASON,
                    MessageStatusEnum.KO,
                    HttpStatus.NOT_FOUND.value(),
                    null

            );
        }

        for (TrackingEntity trackingEntity : trackingEntityList) {
            if (trackingEntity.getClientEntity().getPhoneProfile() == null) {
                logger.warn("NESSUN PROFILE PHONE RECUPERATO ASSOCIATO AL NUMERO: " + numberPhone);
                return new MessageStatus(
                        "NESSUN TRACKING RECUPERATO",
                        "NESSUN PROFILE PHONE RECUPERATO ASSOCIATO AL NUMERO: " + numberPhone,
                        REASON,
                        MessageStatusEnum.KO,
                        HttpStatus.NOT_FOUND.value(),
                        null

                );
            }
            if (trackingEntity.getClientEntity().getPhoneProfile().getNumberPhone().equals(numberPhone)) {
                totalSecondsCall += trackingEntity.getDurationSeconds();
                totalCalls ++;
            } else {
                logger.warn("NESSUN PHONE PROFILE ASSOCIATO AL NUMERO: " + numberPhone);
                return new MessageStatus(
                        "TRACKING NON RECUPERATO",
                        "NESSUN PHONE PROFILE ASSOCIATO AL NUMERO: " + numberPhone,
                        REASON,
                        MessageStatusEnum.KO,
                        HttpStatus.NOT_FOUND.value(),
                        null

                );
            }
        }
        reportModel.setTotalSeconds(totalSecondsCall);
        reportModel.setCalls(totalCalls);
        reportModel.setTrackingEntity(trackingEntityList);
        return new MessageStatus(
                "TRACKING RECUPERATO",
                "TRACKING DEL NUMERO DI CELLULARE: " + numberPhone + " FILTRATI PER IL RANGE TEMPORALE",
                REASON,
                MessageStatusEnum.KO,
                HttpStatus.NOT_FOUND.value(),
                Map.of("report", reportModel)

        );
    }
}
