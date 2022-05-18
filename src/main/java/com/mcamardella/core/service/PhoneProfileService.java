package com.mcamardella.core.service;

import com.mcamardella.core.entity.PhoneProfileEntity;
import com.mcamardella.core.message.MessageStatus;

public interface PhoneProfileService {
    MessageStatus getAllPhoneProfile();
    MessageStatus create(PhoneProfileEntity phoneProfileEntity);
    MessageStatus update(Integer idPhoneProfile, PhoneProfileEntity phoneProfileEntity);
    MessageStatus delete(Integer idPhoneProfile);
    MessageStatus changeStatus(Integer idPhoneProfile, String status);
}
