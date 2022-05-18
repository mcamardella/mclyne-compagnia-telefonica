package com.mcamardella.core.controller;

import com.mcamardella.core.entity.ClientEntity;
import com.mcamardella.core.message.MessageStatus;
import com.mcamardella.core.response.CustomResponse;
import com.mcamardella.core.service.ClientService;
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
@RequestMapping(path = "/client")
public class ClientController {
    private static final Logger logger = LoggerFactory.getLogger(ClientController.class);
    private final ClientService clientService;

    @GetMapping(path = "/all")
    public ResponseEntity<CustomResponse> getAllClients() {
        logger.info("CHIAMATO METODO PER IL GET ALL CLIENTS");
        MessageStatus messageStatus = clientService.getAllClients();
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

    @GetMapping(path = "/id/{idClient}")
    public ResponseEntity<CustomResponse> getClientById(@PathVariable("idClient") Integer idClient) {
        logger.info("CHIAMATO METODO PER IL GET CLIENT BY ID");
        MessageStatus messageStatus = clientService.getClientById(idClient);
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

    @GetMapping(path = "/email/{email}")
    public ResponseEntity<CustomResponse> getClientByEmail(@PathVariable("email") String email) {
        logger.info("CHIAMATO METODO PER IL GET CLIENT BY EMAIL");
        MessageStatus messageStatus = clientService.getClientByEmail(email);
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
    public ResponseEntity<CustomResponse> create(@RequestBody ClientEntity clientEntity) {
        logger.info("CHIAMATO METODO PER IL CREATE CLIENT");
        MessageStatus messageStatus = clientService.create(clientEntity);
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

    @PutMapping(path = "/update/{id}")
    public ResponseEntity<CustomResponse> update(@PathVariable("id") Integer idClient, @RequestBody ClientEntity clientEntity) {
        logger.info("CHIAMATO METODO PER L'UPDATE CLIENT");
        MessageStatus messageStatus = clientService.update(idClient, clientEntity);
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
    public ResponseEntity<CustomResponse> delete(@PathVariable("id") Integer idClient) {
        logger.info("CHIAMATO METODO PER IL DELETE CLIENT");
        MessageStatus messageStatus = clientService.delete(idClient);
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
