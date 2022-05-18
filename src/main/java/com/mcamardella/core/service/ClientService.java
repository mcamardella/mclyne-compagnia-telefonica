package com.mcamardella.core.service;

import com.mcamardella.core.entity.ClientEntity;
import com.mcamardella.core.message.MessageStatus;

public interface ClientService {
    MessageStatus getAllClients();
    MessageStatus create(ClientEntity clientEntity);
    MessageStatus update(Integer idClient, ClientEntity clientEntity);
    MessageStatus delete(Integer idClient);

    MessageStatus getClientById(Integer idClient);
    MessageStatus getClientByEmail(String email);
}
