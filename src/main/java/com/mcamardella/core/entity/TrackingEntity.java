package com.mcamardella.core.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Table(name = "mclyne_tracking")
@Entity
public class TrackingEntity implements Serializable {
    @Id
    @Column(name = "id_tracking")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idTracking;

    @ManyToOne(cascade=CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name="idClient")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ClientEntity clientEntity;

    @Column(name = "date_call_start")
    private LocalDateTime dateCallStart;

    @Column(name = "date_call_end")
    private LocalDateTime dateCallEnd;

    @Column(name = "duration_to_string")
    private String durationToString;

    @Column(name = "duration_seconds")
    private Long durationSeconds;

    public TrackingEntity(ClientEntity clientEntity, LocalDateTime dateCallStart, LocalDateTime dateCallEnd, String durationToString, Long durationSeconds) {
        this.clientEntity = clientEntity;
        this.dateCallStart = dateCallStart;
        this.dateCallEnd = dateCallEnd;
        this.durationToString = durationToString;
        this.durationSeconds = durationSeconds;
    }

    public TrackingEntity(ClientEntity clientEntity, LocalDateTime dateCallStart, LocalDateTime dateCallEnd) {
        this.clientEntity = clientEntity;
        this.dateCallStart = dateCallStart;
        this.dateCallEnd = dateCallEnd;
    }
}
