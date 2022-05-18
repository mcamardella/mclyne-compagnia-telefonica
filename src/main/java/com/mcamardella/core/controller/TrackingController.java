package com.mcamardella.core.controller;


import com.mcamardella.core.entity.ClientEntity;
import com.mcamardella.core.entity.TrackingEntity;
import com.mcamardella.core.message.MessageStatus;
import com.mcamardella.core.response.CustomResponse;
import com.mcamardella.core.service.TrackingService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/tracking")
public class TrackingController {
    private static final Logger logger = LoggerFactory.getLogger(TrackingController.class);
    private final TrackingService trackingService;

    @GetMapping(path = "/all")
    public ResponseEntity<CustomResponse> getAllTracking() {
        logger.info("CHIAMATO METODO PER IL GET ALL TRACKINGS");
        MessageStatus messageStatus = trackingService.getAllTracking();
        return ResponseEntity.ok(
                CustomResponse.builder()
                        .localDateTime(LocalDateTime.now())
                        .httpStatus(HttpStatus.CREATED)
                        .message(messageStatus.getMessage())
                        .developerMessage(messageStatus.getDeveloperMessage())
                        .reason(messageStatus.getReason())
                        .statusCode(HttpStatus.CREATED.value())
                        .data(messageStatus.getData())
                        .build()
        );
    }

    @PostMapping(path = "/create/client/{idClient}")
    public ResponseEntity<CustomResponse> create(@PathVariable("idClient") Integer idClient, @RequestBody TrackingEntity trackingEntity) {
        logger.info("CHIAMATO METODO PER IL CREATE TRACKING");
        MessageStatus messageStatus = trackingService.create(idClient, trackingEntity);
        return ResponseEntity.ok(
                CustomResponse.builder()
                        .localDateTime(LocalDateTime.now())
                        .httpStatus(HttpStatus.CREATED)
                        .message(messageStatus.getMessage())
                        .developerMessage(messageStatus.getDeveloperMessage())
                        .reason(messageStatus.getReason())
                        .statusCode(HttpStatus.CREATED.value())
                        .data(messageStatus.getData())
                        .build()
        );
    }
}
