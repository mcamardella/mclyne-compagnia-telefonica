package com.mcamardella.core.controller;

import com.mcamardella.core.entity.PhoneProfileEntity;
import com.mcamardella.core.message.MessageStatus;
import com.mcamardella.core.response.CustomResponse;
import com.mcamardella.core.service.PhoneProfileService;
import com.mcamardella.core.service.impl.ClientServiceImpl;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/phoneprofile")
public class PhoneProfileController {
    private static final Logger logger = LoggerFactory.getLogger(PhoneProfileController.class);
    private final PhoneProfileService phoneProfileService;

    @GetMapping(path = "/all")
    public ResponseEntity<CustomResponse> getAllPhoneProfle() {
        logger.info("CHIAMATO METODO PER IL GET ALL PHONE PROFILES");
        MessageStatus messageStatus = phoneProfileService.getAllPhoneProfile();
        return ResponseEntity.ok(
                CustomResponse.builder()
                        .localDateTime(LocalDateTime.now())
                        .httpStatus(HttpStatus.OK)
                        .message(messageStatus.getMessage())
                        .developerMessage(messageStatus.getDeveloperMessage())
                        .reason(messageStatus.getReason())
                        .statusCode(HttpStatus.OK.value())
                        .data(messageStatus.getData())
                        .build()
        );
    }

    @PostMapping(path = "/create")
    public ResponseEntity<CustomResponse> create(@RequestBody PhoneProfileEntity phoneProfileEntity) {
        logger.info("CHIAMATO METODO PER IL CREATE PHONE PROFILE");
        MessageStatus messageStatus = phoneProfileService.create(phoneProfileEntity);
        return ResponseEntity.ok(
                CustomResponse.builder()
                        .localDateTime(LocalDateTime.now())
                        .httpStatus(HttpStatus.OK)
                        .message(messageStatus.getMessage())
                        .developerMessage(messageStatus.getDeveloperMessage())
                        .reason(messageStatus.getReason())
                        .statusCode(HttpStatus.OK.value())
                        .data(messageStatus.getData())
                        .build()
        );
    }

    @PutMapping(path = "/update/{id}")
    public ResponseEntity<CustomResponse> update(@PathVariable("id") Integer idPhoneProfile, @RequestBody PhoneProfileEntity phoneProfileEntity) {
        logger.info("CHIAMATO METODO PER L'UPDATE PHONE PROFILE");
        MessageStatus messageStatus = phoneProfileService.update(idPhoneProfile, phoneProfileEntity);
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

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<CustomResponse> delete(@PathVariable("id") Integer idPhoneProfile) {
        logger.info("CHIAMATO METODO PER IL DELETE PHONE PROFILE BY ID");
        MessageStatus messageStatus = phoneProfileService.delete(idPhoneProfile);
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

    @PutMapping(path = "/{idPhoneProfile}/changestatus/status/{status}")
    public ResponseEntity<CustomResponse> update(@PathVariable("idPhoneProfile") Integer idPhoneProfile, @PathVariable("status") String status) {
        logger.info("CHIAMATO METODO PER IL CHANGE STATUS DEL PHONE PROFILE");
        MessageStatus messageStatus = phoneProfileService.changeStatus(idPhoneProfile, status);
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
