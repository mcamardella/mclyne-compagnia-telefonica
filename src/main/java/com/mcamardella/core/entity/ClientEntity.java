package com.mcamardella.core.entity;

import com.mcamardella.core.enumeration.GenderEnum;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Table(name = "mclyne_client")
@Entity
public class ClientEntity implements Serializable {
    @Id
    @Column(name = "id_client")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idClient;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "dateOfBirth")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dateOfBirth;

    @Column(name = "gender")
    private GenderEnum gender;

    @Column(name = "fiscal_code")
    private String fiscalCode;

    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;

    @Column(name = "province")
    private String province;

    @Column(name = "postCode")
    private String postCode;

    @Column(name = "country")
    private String country;

    @Column(name = "email")
    private String email;

    @Column(name = "active")
    private Boolean active;

    @Column(name = "created")
    private LocalDateTime created;

    @Column(name = "updated")
    private LocalDateTime updated;

    @ManyToOne (cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="idPhoneProfile")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private PhoneProfileEntity phoneProfile;

    public ClientEntity(String firstName, String lastName, LocalDate dateOfBirth, GenderEnum gender, String fiscalCode, String address, String city, String province, String postCode, String country, String email, Boolean active, LocalDateTime created, LocalDateTime updated, PhoneProfileEntity phoneProfile) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.fiscalCode = fiscalCode;
        this.address = address;
        this.city = city;
        this.province = province;
        this.postCode = postCode;
        this.country = country;
        this.email = email;
        this.active = active;
        this.created = created;
        this.updated = updated;
        this.phoneProfile = phoneProfile;
    }

    public ClientEntity(String firstName, String lastName, LocalDate dateOfBirth, GenderEnum gender, String fiscalCode, String address, String city, String province, String postCode, String country, String email, Boolean active, LocalDateTime created, LocalDateTime updated) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.fiscalCode = fiscalCode;
        this.address = address;
        this.city = city;
        this.province = province;
        this.postCode = postCode;
        this.country = country;
        this.email = email;
        this.active = active;
        this.created = created;
        this.updated = updated;
    }
}
