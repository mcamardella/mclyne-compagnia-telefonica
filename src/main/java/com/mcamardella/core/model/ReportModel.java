package com.mcamardella.core.model;

import com.mcamardella.core.entity.TrackingEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportModel {
    private List<TrackingEntity> trackingEntity;
    private Integer calls;
    private Long totalSeconds;
}
