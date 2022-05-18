package com.mcamardella.core.service;

import com.mcamardella.core.entity.TrackingEntity;
import com.mcamardella.core.message.MessageStatus;

public interface TrackingService {
    MessageStatus getAllTracking();
    MessageStatus create(Integer idClient, TrackingEntity trackingEntity);
}
