package ru.learnup.nexigntask.callscdrplus.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tariff")
@Getter
@Setter
public class Tariff {

    @Id
    private String tariffId;

    @Column(name = "tariff_name")
    private String tariffName;

    @Column(name = "subscription_fee")
    private Integer subscriptionFee;

    @Column(name = "benefit_minutes")
    private Integer benefitMinutes;

    @JoinColumn
    @OneToOne
    private Client client;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private CallCost inCallCostId;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private CallCost outCallCostId;
}
