package com.mcamardella.core.repository;

import com.mcamardella.core.entity.TrackingEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TrackingRepository extends CrudRepository<TrackingEntity, Integer> {
    List<TrackingEntity> findByClientEntity_PhoneProfileNumberPhone(String numberPhone);
    List<TrackingEntity> findTrackingEntitiesByDateCallStartIsBetween(LocalDateTime dateCallStart, LocalDateTime dateCallEnd);
}
