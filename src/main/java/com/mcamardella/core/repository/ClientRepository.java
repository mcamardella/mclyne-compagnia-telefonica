package com.mcamardella.core.repository;

import com.mcamardella.core.entity.ClientEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends CrudRepository<ClientEntity, Integer> {
    ClientEntity getClientEntitiesByEmail(String email);
}
