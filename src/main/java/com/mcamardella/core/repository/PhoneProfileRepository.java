package com.mcamardella.core.repository;

import com.mcamardella.core.entity.PhoneProfileEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneProfileRepository extends CrudRepository<PhoneProfileEntity, Integer> {
}
