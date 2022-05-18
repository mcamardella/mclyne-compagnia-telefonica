package com.mcamardella.core.entity;

import com.mcamardella.core.enumeration.StatusPhoneLineEnum;
import com.mcamardella.core.enumeration.TypologyLineEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Table(name = "mclyne_phone_profile")
@Entity
public class PhoneProfileEntity implements Serializable {
    @Id
    @Column(name = "id_phone_profile")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idPhoneProfile;

    @Column(name = "prefix")
    private String prefix;

    @Column(name = "number_phone")
    private String numberPhone;

    @Column(name = "typology_line")
    private TypologyLineEnum typologyLine;

    @Column(name = "status_phone_line")
    private StatusPhoneLineEnum statusPhoneLine;

    @Column(name = "assigned")
    private LocalDateTime assigned;

    @Column(name = "updated")
    private LocalDateTime updated;

    public PhoneProfileEntity(String prefix, String numberPhone, TypologyLineEnum typologyLine, StatusPhoneLineEnum statusPhoneLine, LocalDateTime assigned, LocalDateTime updated) {
        this.prefix = prefix;
        this.numberPhone = numberPhone;
        this.typologyLine = typologyLine;
        this.statusPhoneLine = statusPhoneLine;
        this.assigned = assigned;
        this.updated = updated;
    }
}
