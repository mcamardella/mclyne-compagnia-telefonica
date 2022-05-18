package com.mcamardella.core.controller;

import com.mcamardella.core.message.MessageStatus;
import com.mcamardella.core.response.CustomResponse;
import com.mcamardella.core.service.AssignmentService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/assignment")
public class AssignmentController {
    private static final Logger logger = LoggerFactory.getLogger(AssignmentController.class);
    private final AssignmentService assignmentService;

    @PutMapping(path = "/client/{idClient}/phoneprofile/{idPhoneProfile}")
    public ResponseEntity<CustomResponse> assignPhoneProfile(@PathVariable("idClient") Integer idClient, @PathVariable("idPhoneProfile") Integer idPhoneProfile) {
        logger.info("CHIAMATO METODO PER L'ASSEGNAZIONE DEL PROFILE PHONE AL CLIENT");
        MessageStatus messageStatus = assignmentService.assignPhoneProfile(idClient, idPhoneProfile);
        return ResponseEntity.ok(
                CustomResponse.builder()
                        .localDateTime(LocalDateTime.now())
                        .httpStatus(HttpStatus.OK)
                        .message(messageStatus.getMessage())
                        .developerMessage(messageStatus.getDeveloperMessage())
                        .reason(messageStatus.getReason())
                        .statusCode(messageStatus.getStatusCode())
                        .build()
        );
    }

    @PutMapping(path = "/remove/client/{idClient}/phoneprofile/{idPhoneProfile}")
    public ResponseEntity<CustomResponse> removePhoneProfile(@PathVariable("idClient") Integer idClient, @PathVariable("idPhoneProfile") Integer idPhoneProfile) {
        logger.info("CHIAMATO METODO PER LA RIMOZIONE DELL?ASSOCIAZIONE DEL PROFILE PHONE AL CLIENT");
        MessageStatus messageStatus = assignmentService.removePhoneProfile(idClient, idPhoneProfile);
        return ResponseEntity.ok(
                CustomResponse.builder()
                        .localDateTime(LocalDateTime.now())
                        .httpStatus(HttpStatus.OK)
                        .message(messageStatus.getMessage())
                        .developerMessage(messageStatus.getDeveloperMessage())
                        .reason(messageStatus.getReason())
                        .statusCode(messageStatus.getStatusCode())
                        .build()
        );
    }
}
