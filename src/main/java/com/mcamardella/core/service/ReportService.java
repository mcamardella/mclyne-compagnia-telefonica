package com.mcamardella.core.service;

import com.mcamardella.core.message.MessageStatus;

import java.time.LocalDateTime;

public interface ReportService {
    MessageStatus getTrackingByNumberPhone(String numberPhone);
    MessageStatus getTrackingByNumberPhoneAndRangeTemporal(String numberPhone, LocalDateTime start, LocalDateTime end);
}
