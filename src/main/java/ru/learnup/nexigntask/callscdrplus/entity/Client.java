package ru.learnup.nexigntask.callscdrplus.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "clients")
@Getter
@Setter
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "balance")
    private Double balance;

    @Column(name = "benefit_minutes_left")
    private Integer benefitMinutesLeft;

    @OneToOne(mappedBy = "tariff", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Tariff tariff;

}
