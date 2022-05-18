package com.mcamardella.core.controller;

import com.mcamardella.core.entity.TrackingEntity;
import com.mcamardella.core.message.MessageStatus;
import com.mcamardella.core.repository.TrackingRepository;
import com.mcamardella.core.response.CustomResponse;
import com.mcamardella.core.service.ReportService;
import com.mcamardella.core.service.impl.ClientServiceImpl;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/report")
public class ReportController {
    private static final Logger logger = LoggerFactory.getLogger(ReportController.class);
    private final ReportService reportService;

    @Autowired
    TrackingRepository trackingRepository;

    @GetMapping(path = "/number/{numberPhone}")
    public ResponseEntity<CustomResponse> getTrackingByNumberPhone(@PathVariable("numberPhone") String numberPhone) {
        logger.info("CHIAMATO METODO PER IL GET TRACKING BY NUMBER PHONE");
        MessageStatus messageStatus = reportService.getTrackingByNumberPhone(numberPhone);
        return ResponseEntity.ok(
                CustomResponse.builder()
                        .localDateTime(LocalDateTime.now())
                        .httpStatus(HttpStatus.OK)
                        .message(messageStatus.getMessage())
                        .developerMessage(messageStatus.getDeveloperMessage())
                        .reason(messageStatus.getReason())
                        .statusCode(messageStatus.getStatusCode())
                        .data(messageStatus.getData())
                        .build()
        );
    }

    @GetMapping(path = "/number/{numberPhone}/start/{start}/end/{end}")
    public ResponseEntity<CustomResponse> getTrackingByNumberPhoneAndRangeTemporal(
            @PathVariable("numberPhone") String numberPhone,
            @PathVariable("start") String start,
            @PathVariable("end") String end) {
        logger.info("CHIAMATO METODO PER IL GET TRACKING BY NUMBER PHONE AND RANGE TEMPORAL");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm:s");
        LocalDateTime dateStart = LocalDateTime.parse(start, formatter);
        LocalDateTime dateEnd = LocalDateTime.parse(end, formatter);

        MessageStatus messageStatus = reportService.getTrackingByNumberPhoneAndRangeTemporal(numberPhone, dateStart, dateEnd);
        return ResponseEntity.ok(
                CustomResponse.builder()
                        .localDateTime(LocalDateTime.now())
                        .httpStatus(HttpStatus.OK)
                        .message(messageStatus.getMessage())
                        .developerMessage(messageStatus.getDeveloperMessage())
                        .reason(messageStatus.getReason())
                        .statusCode(messageStatus.getStatusCode())
                        .data(messageStatus.getData())
                        .build()
        );
    }
}
