package com.mcamardella.core.service;

import com.mcamardella.core.message.MessageStatus;

public interface AssignmentService {
    MessageStatus assignPhoneProfile(Integer idClient, Integer idPhoneProfile);
    MessageStatus removePhoneProfile(Integer idClient, Integer idPhoneProfile);
}
